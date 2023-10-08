package com.example.vanesa.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/saludo")
public class SaludoController {
    @GetMapping("/hello")
    public String hello(){
        return "Hola mi nombre es Vanesa";
    }


    @PostMapping("/goodbye")
    public String goodbye() {
        return "Hasta luego!";
    }

}
