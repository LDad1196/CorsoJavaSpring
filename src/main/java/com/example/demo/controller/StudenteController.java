package com.example.demo.controller;

import com.example.demo.entity.Studente;
import com.example.demo.service.StudenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/studenti")
public class StudenteController {

    @Autowired
    StudenteService studenteService;

    @GetMapping("/lista")
    public ModelAndView list() {
        List<Studente> studenti = studenteService.findAll();
        ModelAndView mav = new ModelAndView("list-studenti");
        mav.addObject("studenti", studenti);
        return mav;
    }

    @GetMapping("/nuovo")
    public ModelAndView showAdd() {
        ModelAndView mav = new ModelAndView("form-studente");
        mav.addObject("studente", new Studente());
        return mav;
    }

    @PostMapping("/salva")
    public ModelAndView create(@Valid @ModelAttribute("studente") Studente studente,
                               BindingResult br) {
        if (br.hasErrors()) {
            ModelAndView mav = new ModelAndView("form-studente");
            mav.addObject("studente", studente);
            return mav;
        }
        studenteService.save(studente);
        return new ModelAndView("redirect:/studenti/lista");
    }

    @GetMapping("/{id_studente}/edit")
    public ModelAndView showEdit(@PathVariable("id_studente") Integer id_studente) {
        Studente studente = studenteService.findById(id_studente);
        ModelAndView mav = new ModelAndView("form-studente");
        mav.addObject("studente", studente);
        return mav;
    }

    @GetMapping("/{id_studente}/delete")
    public ModelAndView delete(@PathVariable("id_studente") Integer id_studente) {
        studenteService.delete(id_studente);
        return new ModelAndView("redirect:/studenti/lista");
    }

    @GetMapping("/cerca")
    public ModelAndView cerca(@RequestParam("nome") String nome) {
        List<Studente> risultati = studenteService.cercaPerNome(nome);
        ModelAndView mav = new ModelAndView("list-studenti");
        mav.addObject("studenti", risultati);
        return mav;
    }
}
