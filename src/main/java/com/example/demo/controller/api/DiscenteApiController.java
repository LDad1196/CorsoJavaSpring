package com.example.demo.controller.api;

import com.example.demo.data.DTO.DiscenteCompletoDTO;
import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discenti")
public class DiscenteApiController {

    @Autowired
    DiscenteService discenteService;

    @GetMapping("/list")
    public Iterable<DiscenteDTO> list () {
        return discenteService.findAllSintetico();
    }

    @GetMapping("/{id_discente}")
    public DiscenteDTO getById(@PathVariable("id_discente") Integer id_discente) {
        return discenteService.findById(id_discente);
    }

    @PostMapping
    public DiscenteCompletoDTO create(@RequestBody DiscenteCompletoDTO discente) {
        discenteService.save(discente);
        return discente;
    }

    @PutMapping("/{id_discente}")
    public DiscenteCompletoDTO update(@PathVariable("id_discente") Integer id_discente,
                                      @RequestBody @Valid DiscenteCompletoDTO discente) {
        discente.setId_discente(id_discente);
        return discenteService.update(id_discente, discente);
    }

    @DeleteMapping("{id_discente}")
    public void delete(@PathVariable("id_discente") Integer id_discente) {
        discenteService.deleteById(id_discente);
    }
}
