package com.example.demo.controller;

import com.example.demo.data.DTO.DiscenteCompletoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/discenti")
public class DiscenteController {

    @Autowired
    private DiscenteService discenteService;

    // LISTA
    @GetMapping("/lista")
    public ModelAndView list() {
        List<DiscenteDTO> discenti = discenteService.findAllSintetico();
        ModelAndView mav = new ModelAndView("list-discenti");
        mav.addObject("discenti", discenti);
        return mav;
    }

    // FORM NUOVO
    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", new DiscenteCompletoDTO());
        return mav;
    }

    // SALVA
    @PostMapping("/salva")
    public ModelAndView create(@Valid @ModelAttribute("discente") DiscenteCompletoDTO dto,
                               BindingResult br) {
        if (br.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("form-discente");
            modelAndView.addObject("discente", dto);
            return modelAndView;
        }
        discenteService.save(dto);
        return new ModelAndView("redirect:/discenti/lista");
    }

    // FORM EDIT
    @GetMapping("/{id_discente}/edit")
    public ModelAndView showEdit(@PathVariable("id_discente") Integer id_discente) {
        DiscenteCompletoDTO dto = discenteService.findByIdCompleto(id_discente);
        ModelAndView modelAndView = new ModelAndView("form-discente");
        modelAndView.addObject("discente", dto);
        return modelAndView;
    }

    // DELETE
    @GetMapping("/{id_discente}/delete")
    public ModelAndView delete(@PathVariable("id_discente") Integer id_discente) {
        discenteService.deleteByIdConRimozioneDaCorsi(id_discente);
        return new ModelAndView("redirect:/discenti/lista");
    }

    // CERCA
    @GetMapping("/cerca")
    public ModelAndView cerca(@RequestParam("nome") String nome) {
        List<DiscenteDTO> risultati = discenteService.cercaPerNome(nome);
        ModelAndView modelAndView = new ModelAndView("list-discenti");
        modelAndView.addObject("discenti", risultati);
        return modelAndView;
    }

}