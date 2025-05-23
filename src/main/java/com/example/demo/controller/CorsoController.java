package com.example.demo.controller;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    @Autowired
    DocenteService docenteService;

    @Autowired
    DiscenteService discenteService;

    //CHIAMATE GET
    //Visualizza lista
    @GetMapping("/lista")
    public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView("list-corsi");
        modelAndView.addObject("corsi", corsoService.findAll());
        modelAndView.addObject("docentiMap", docenteService.getMappaDocenti());
        modelAndView.addObject("discentiMap", discenteService.getMappaDiscenti());
        return modelAndView;
    }

    //Visualizza form nuovo corso
    @GetMapping("/nuovo")
    public ModelAndView mostraFormNuovoCorso() {
        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", new CorsoDTO());
        modelAndView.addObject("docenti", docenteService.findAllSintetico());
        modelAndView.addObject("discenti", discenteService.findAllSintetico());
        return modelAndView;
    }

    //Chiamata form di modifica del corso, con modifica discenti
    @GetMapping("/{id_corso}/edit")
    public ModelAndView showEdit(@PathVariable("id_corso") Integer id_corso) {
        CorsoDTO corso = corsoService.findById(id_corso);
        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", corso);
        modelAndView.addObject("docenti", docenteService.findAllSintetico());
        modelAndView.addObject("discenti", discenteService.findAllSintetico());
        return modelAndView;
    }

    //Delete corso
    @GetMapping("{id_corso}/delete")
    public ModelAndView delete(@PathVariable("id_corso") Integer id_corso) {
        corsoService.delete(id_corso);
        return new ModelAndView("redirect:/corsi/lista");
    }

    //CHIAMATE POST
    //Salvataggio nuovo corso, usato anche per il salvataggio della modifica
    @PostMapping("/salva")
    public ModelAndView create(@ModelAttribute("corso") CorsoDTO corsoDTO,
                               BindingResult br) {
        if (br.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("form-corso");
            modelAndView.addObject("corso", corsoDTO);
            modelAndView.addObject("docenti", docenteService.findAllSintetico());
            modelAndView.addObject("discenti", discenteService.findAllSintetico());
            return modelAndView;
        }
        corsoService.save(corsoDTO);
        return new ModelAndView("redirect:/corsi/lista");
    }

}
