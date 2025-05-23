package com.example.demo.controller.api;

import com.example.demo.data.DTO.DocenteCompletoDTO;
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/docenti")
public class DocenteApiController {

    @Autowired
    DocenteService docenteService;

    @GetMapping("/list")
    public Iterable<DocenteDTO> list() {
        return docenteService.findAllSintetico();
    }

    @GetMapping("/{id_docente}")
    public DocenteDTO getById(@PathVariable("id_docente") Integer id_docente) {
        return docenteService.findById(id_docente);
    }

    @PostMapping
    public DocenteCompletoDTO create(@RequestBody DocenteCompletoDTO docente) {
        docenteService.save(docente);
        return docente;
    }

    @PutMapping("/{id_docente}")
    public DocenteCompletoDTO update(@PathVariable("id_docente") Integer id_docente,
                                     @RequestBody DocenteCompletoDTO docente) {
        docente.setId_docente(id_docente);
        docenteService.save(docente);
        return docente;
    }

    @DeleteMapping("{id_docente}")
    public void delete(@PathVariable("id_docente") Integer id_docente) {
        docenteService.deleteById(id_docente);
    }
}
