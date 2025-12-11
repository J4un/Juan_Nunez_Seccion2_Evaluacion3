package com.muebleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebControllerCliente {

    @GetMapping("/cliente")
    public String clienteHome() {
        return "cliente/index-cliente";
    }

    @GetMapping("/catalogo")
    public String catalogo() {
        return "cliente/catalogo";
    }

    @GetMapping("/catalogo/mueble/{id}")
    public String verMueble(@PathVariable Long id, Model model) {
        model.addAttribute("muebleId", id);
        return "cliente/mueble-detalle";
    }

    @GetMapping("/cotizar")
    public String cotizar() {
        return "cliente/cotizar";
    }

    @GetMapping("/compras")
    public String comprasCliente() {
        return "cliente/compras";
    }

}
