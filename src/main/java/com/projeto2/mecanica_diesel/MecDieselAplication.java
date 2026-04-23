package com.projeto2.mecanica_diesel;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")

public class MecDieselAplication {
    @GetMapping("/hello")
    public String hello() {
        return "oiii!";
    }
}
