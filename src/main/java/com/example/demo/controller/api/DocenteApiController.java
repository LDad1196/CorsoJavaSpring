package com.example.demo.controller.api;

import com.example.demo.data.DTO.DocenteCompletoDTO;
import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.service.DocenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docenti")
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

    @GetMapping
    public List<DocenteDTO> getDocentiByNomeAndCognome(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cognome) {

        if (nome != null && cognome != null) {
            return docenteService.getDocentiByNomeAndCognome(nome, cognome);
        }

        // Se non ci sono parametri, restituisce tutti i docenti
        return docenteService.findAllSintetico();
    }

    // Endpoint per cercare un singolo docente per nome e cognome
    @GetMapping("/search")
    public DocenteDTO getDocenteByNomeAndCognome(
            @RequestParam String nome,
            @RequestParam String cognome) {
        return docenteService.getDocenteByNomeAndCognome(nome, cognome);
    }

    @PostMapping
    public DocenteCompletoDTO create(@RequestBody DocenteCompletoDTO docente) {
        docenteService.save(docente);
        return docente;
    }

    @PutMapping("/{id_docente}")
    public DocenteCompletoDTO update(@PathVariable("id_docente") Integer id_docente,
                                     @RequestBody @Valid DocenteCompletoDTO docente) {
        docente.setId_docente(id_docente);
        return docenteService.update(id_docente, docente);
    }


    @DeleteMapping("{id_docente}")
    public void delete(@PathVariable("id_docente") Integer id_docente) {
        docenteService.deleteById(id_docente);
    }
}
