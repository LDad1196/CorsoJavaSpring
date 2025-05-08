package com.example.demo.controller;

import com.example.demo.entity.Corso;
import com.example.demo.entity.Docente;
import com.example.demo.entity.Discente;
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
        List<Corso> corsi = corsoService.findAll();
        ModelAndView modelAndView = new ModelAndView("list-corsi");
        modelAndView.addObject("corsi", corsi);
        return modelAndView;
    }

    //Visualizza form nuovo corso
    @GetMapping("/nuovo")
    public ModelAndView mostraFormNuovoCorso() {
        Corso corso = new Corso();
        ModelAndView mav = new ModelAndView("form-corso"); // nome del JSP nella cartella /WEB-INF/jsp/
        mav.addObject("corso", corso);
        mav.addObject("docenti", docenteService.findAll());
        mav.addObject("tuttiDiscenti", discenteService.findAll());
        mav.addObject("discentiIscritti", corso.getDiscenti());
        return mav;
    }

    //Chiamata form di modifica del corso, con modifica discenti
    @GetMapping("/{id_corso}/edit")
    public ModelAndView showEdit(@PathVariable("id_corso") Integer id_corso) {
        Corso corso = corsoService.findById(id_corso);
        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", corso);
        modelAndView.addObject("docenti", docenteService.findAll());
        modelAndView.addObject("tuttiDiscenti", discenteService.findAll());
        modelAndView.addObject("discentiIscritti", corso.getDiscenti());
        return modelAndView;
    }

    //Show singolo corso, ancora da implementare con jsp
    @GetMapping("/{id_corso}")
    public ModelAndView  mostraCorso(@PathVariable("id_corso") Integer id_corso) {
        Corso corso = corsoService.findById(id_corso);
        ModelAndView modelAndView = new ModelAndView("show-corso");
        modelAndView.addObject("corso", corso);
        modelAndView.addObject("discenti", corso.getDiscenti());
        modelAndView.addObject("tuttiDiscenti", discenteService.findAll());
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
    public ModelAndView create(@ModelAttribute("corso") Corso corso,
                               BindingResult br) {
        if (br.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("form-corso");
            modelAndView.addObject("corso", corso);
            return modelAndView;
        }
        Docente docente = docenteService.findById(corso.getDocente().getId_docente());
        corso.setDocente(docente);
        if (corso.getId_corso() != null) {
            Corso corsoEsistente = corsoService.findById(corso.getId_corso());
            corso.setDiscenti(corsoEsistente.getDiscenti()); // 🔥 mantieni gli discenti già associati
        }
        corsoService.save(corso);
        return new ModelAndView("redirect:/corsi/lista");
    }

    //Salvataggio discente
    @PostMapping("/{id_corso}/aggiungiDiscente")
    public String aggiungiDiscente(@PathVariable("id_corso") Integer id_corso,
                                   @RequestParam("discenteId") Integer discenteId) {
        Corso corso = corsoService.findById(id_corso);
        Discente discente = discenteService.findById(discenteId);
        if (corso != null && discente != null) {
            corso.getDiscenti().add(discente);
            discente.getCorsi().add(corso);
            corsoService.save(corso);
            discenteService.save(discente);
        }
        return "redirect:/corsi/" + id_corso + "/edit";
    }

    //Eliminazione discente nell'edit
    @PostMapping("/{id_corso}/rimuoviDiscente")
    public ModelAndView rimuoviDiscente(@PathVariable("id_corso") Integer id_corso,
                                        @RequestParam("id_discente") Integer id_discente) {
        Corso corso = corsoService.findById(id_corso);
        Discente discente = discenteService.findById(id_discente);

        if (corso != null && discente != null) {
            corso.getDiscenti().remove(discente);
            discente.getCorsi().remove(corso);
            corsoService.save(corso);
            discenteService.save(discente);
        }

        ModelAndView modelAndView = new ModelAndView("form-corso");
        modelAndView.addObject("corso", corso);
        modelAndView.addObject("docenti", docenteService.findAll());
        modelAndView.addObject("tuttiDiscenti", discenteService.findAll());
        modelAndView.addObject("discentiIscritti", corso.getDiscenti());
        return modelAndView;
    }
}
