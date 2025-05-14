package com.example.demo.controller;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.data.entity.Corso;
import com.example.demo.data.entity.Docente;
import com.example.demo.data.entity.Discente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/corsi")
public class CorsoController {

    @Autowired
    CorsoService corsoService;

    @Autowired
    DocenteService docenteService;

    @Autowired
    DiscenteService discenteService;

    //Metodi get
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
        ModelAndView modelAndView = new ModelAndView("form-corso"); // nome del JSP nella cartella /WEB-INF/jsp/
        modelAndView.addObject("corso", new CorsoDTO());
        modelAndView.addObject("docenti", docenteService.findAll());
        modelAndView.addObject("tuttiDiscenti", discenteService.findAll());
        return modelAndView;
    }

    //Chiamata form di modifica del corso, con modifica discenti
    @GetMapping("/{id_corso}/edit")
    public ModelAndView showEdit(@PathVariable("id_corso") Integer id_corso) {
        CorsoDTO corso = corsoService.findById(id_corso);
        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", corso);
        modelAndView.addObject("docenti", docenteService.findAll());
        modelAndView.addObject("discenti", discenteService.findAll());
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
            modelAndView.addObject("docenti", docenteService.findAll());
            modelAndView.addObject("discenti", discenteService.findAll());
            return modelAndView;
        }
        corsoService.save(corsoDTO);
        return new ModelAndView("redirect:/corsi/lista");
    }

}
