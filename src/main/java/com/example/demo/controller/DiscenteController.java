package com.example.demo.controller;

import com.example.demo.data.entity.Discente;
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
    DiscenteService discenteService;

    @GetMapping("/lista")
    public ModelAndView list() {
        List<Discente> discenti = discenteService.findAll();
        ModelAndView mav = new ModelAndView("list-discenti");
        mav.addObject("discenti", discenti);
        return mav;
    }

    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", new Discente());
        return mav;
    }

    @PostMapping("/salva")
    public ModelAndView create(@Valid @ModelAttribute("discente") Discente discente,
                               BindingResult br) {
        if (br.hasErrors()) {
            ModelAndView mav = new ModelAndView("form-discente");
            mav.addObject("discente", discente);
            return mav;
        }
        discenteService.save(discente);
        return new ModelAndView("redirect:/discenti/lista");
    }

    @GetMapping("/{id_discente}/edit")
    public ModelAndView showEdit(@PathVariable("id_discente") Integer id_discente) {
        Discente discente = discenteService.findById(id_discente);
        ModelAndView mav = new ModelAndView("form-discente");
        mav.addObject("discente", discente);
        return mav;
    }

    @GetMapping("/{id_discente}/delete")
    public ModelAndView delete(@PathVariable("id_discente") Integer id_discente) {
        discenteService.deleteByIdConRimozioneDaCorsi(id_discente);
        return new ModelAndView("redirect:/discenti/lista");
    }

    @GetMapping("/cerca")
    public ModelAndView cerca(@RequestParam("nome") String nome) {
        List<Discente> risultati = discenteService.cercaPerNome(nome);
        ModelAndView mav = new ModelAndView("list-discenti");
        mav.addObject("discenti", risultati);
        return mav;
    }
}
