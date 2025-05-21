package com.example.demo.controller.api;

import com.example.demo.data.DTO.DocenteDTO;
import com.example.demo.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/docenti")
public class DocenteApiController {

    @Autowired
    DocenteService docenteService;

    @RequestMapping("/list")
    public Iterable<DocenteDTO> getAll() {
        return docenteService.findAllSintetico();
    }
}
