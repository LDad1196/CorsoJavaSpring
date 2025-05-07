package com.example.demo.controller;

import com.example.demo.entity.Docente;
import com.example.demo.entity.Studente;
import com.example.demo.service.StudenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/studenti")
public class StudenteController {

    @Autowired
    StudenteService studenteService;

    @GetMapping("/lista")
    public String list (Model model) {
        List<Studente> studenti = new ArrayList<>();
        studenti = studenteService.findAll();
        model.addAttribute("studenti", studenti);
        return "list-studenti";
    }

    @GetMapping("/nuovo")
    public String showAdd(Model model) {
        model.addAttribute("studente", new Studente());
        return "form-studente";
    }

    @PostMapping("/salva")
    public String create(@Valid @ModelAttribute("studente") Studente studente,
                         BindingResult br) {
        if (br.hasErrors()) return "form-studente";
        studenteService.save(studente);
        return "redirect:/studenti/lista";
    }

    @GetMapping("/{id_studente}/edit")
    public String showEdit(@PathVariable("id_studente") Integer id_studente, Model model) {
        Studente studente = studenteService.findById(id_studente);
        model.addAttribute("studente", studente);
        return "form-studente";
    }

    @GetMapping("/{id_studente}/delete")
    public String delete(@PathVariable("id_studente") Integer id_studente) {
        studenteService.delete(id_studente);
        return "redirect:/studenti/lista";
    }





}
