package com.example.demo.controller.api;

import com.example.demo.data.DTO.CorsoDTO;
import com.example.demo.service.CorsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsoApiController {

    @Autowired
    CorsoService corsoService;

    @RequestMapping("/api/corsi/list")
    public Iterable<CorsoDTO> list () {
        return corsoService.findAll();
    }
}
