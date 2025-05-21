package com.example.demo.controller.api;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/corsi")
public class CorsoApiController {

    @Autowired
    CorsoService corsoService;

    @RequestMapping("/list")
    public Iterable<CorsoDTO> list () {
        return corsoService.findAll();
    }
}
