package com.uniovi.sdi2223entrega182.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    /**
     * Método que retorna a la vista principal del sistema.
     * @return la vista mencionada
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/home")
    public String home() {
        return "index";
    }
}
