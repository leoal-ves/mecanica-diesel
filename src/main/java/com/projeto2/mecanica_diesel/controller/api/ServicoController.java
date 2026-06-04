package com.projeto2.mecanica_diesel.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/api/servicos")
public class ServicoController {

    @PostMapping()
    public String postMethodName(@RequestBody String entity) {
        
        return entity;
    }
    @GetMapping()
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
}
