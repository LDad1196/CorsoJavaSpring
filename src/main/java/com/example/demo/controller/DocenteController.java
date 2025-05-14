package com.example.demo.controller;

import com.example.demo.data.DTO.DocenteCompletoDTO;
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.service.DocenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/docenti")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    // LISTA
    @GetMapping("/lista")
    public String list(Model model) {
        List<DocenteDTO> docenti = docenteService.findAllSintetico();
        model.addAttribute("docenti", docenti);
        return "list-docenti";
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("docente", new DocenteCompletoDTO());
        return "form-docente";
    }

    // SALVA
    @PostMapping("/salva")
    public String create(@Valid @ModelAttribute("docente") DocenteCompletoDTO docenteDTO,
                         BindingResult br) {
        if (br.hasErrors()) return "form-docente";
        docenteService.save(docenteDTO);
        return "redirect:/docenti/lista";
    }

    // FORM EDIT
    @GetMapping("/{id_docente}/edit")
    public String showEdit(@PathVariable("id_docente") Integer id_docente, Model model) {
        DocenteCompletoDTO docenteDTO = docenteService.findByIdCompleto(id_docente);
        model.addAttribute("docente", docenteDTO);
        return "form-docente";
    }

    // DELETE
    @GetMapping("/{id_docente}/delete")
    public String delete(@PathVariable("id_docente") Integer id_docente) {
        docenteService.deleteById(id_docente);
        return "redirect:/docenti/lista";
    }

    // CERCA
    @GetMapping("/cerca")
    public String cerca(@RequestParam("nome") String nome, Model model) {
        List<DocenteDTO> risultati = docenteService.cercaPerNome(nome);
        model.addAttribute("docenti", risultati);
        return "list-docenti";
    }
}


