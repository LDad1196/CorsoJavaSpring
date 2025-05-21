package com.example.demo.controller.api;

import com.example.demo.data.DTO.DiscenteDTO;
import com.example.demo.service.DiscenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/discenti")
public class DiscenteApiController {

    @Autowired
    DiscenteService discenteService;

    @RequestMapping("/list")
    public Iterable<DiscenteDTO> list () {
        return discenteService.findAllSintetico();
    }
}
