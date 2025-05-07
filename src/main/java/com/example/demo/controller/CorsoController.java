package com.example.demo.controller;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.service.CorsoService;
import com.example.demo.service.DocenteService;
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

    @GetMapping("/lista")
    public ModelAndView list() {
        List<Corso> corsi = corsoService.findAll();
        ModelAndView modelAndView = new ModelAndView("list-corsi");
        modelAndView.addObject("corsi", corsi);
        return modelAndView;
    }

    @GetMapping("/nuovo")
    public ModelAndView mostraFormNuovoCorso() {
        ModelAndView mav = new ModelAndView("form-corso"); // nome del JSP nella cartella /WEB-INF/jsp/
        mav.addObject("corso", new Corso());
        mav.addObject("docenti", docenteService.findAll());
        return mav;
    }

    @PostMapping("/salva")
    public ModelAndView create(@ModelAttribute("corso") Corso corso,
                               BindingResult br) {
        if (br.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("form-corso");
            modelAndView.addObject("corso", corso);
            return modelAndView;
        }
        Docente docente = docenteService.findById(corso.getDocente().getId_docente());
        corso.setDocente(docente);
        corsoService.save(corso);
        return new ModelAndView("redirect:/corsi/lista");
    }

    @GetMapping("/{id_corso}/edit")
    public ModelAndView showEdit(@PathVariable("id_corso") Integer id_corso) {
        Corso corso = corsoService.findById(id_corso);
        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", corso);
        modelAndView.addObject("docenti", docenteService.findAll());
        return modelAndView;
    }

    @GetMapping("{id_corso}/delete")
    public ModelAndView delete(@PathVariable("id_corso") Integer id_corso) {
        corsoService.delete(id_corso);
        return new ModelAndView("redirect:/corsi/lista");
    }



}
