package com.muebleria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebControllerAdmin {

    @GetMapping("/admin")
    public String adminHome() {
        return "admin/index-admin";
    }

    // CRUD muebles
    @GetMapping("/admin/muebles")
    public String adminMuebles() {
        return "admin/muebles-admin";
    }

    @GetMapping("/admin/muebles/nuevo")
    public String adminNuevoMueble() {
        return "admin/mueble-form-admin";
    }

    @GetMapping("/admin/muebles/{id}/editar")
    public String adminEditarMueble(@PathVariable Long id, Model model) {
        model.addAttribute("muebleId", id);
        return "admin/mueble-form-admin";
    }

    // Variantes
    @GetMapping("/admin/muebles/{id}/variantes")
    public String adminVariantes(@PathVariable Long id, Model model) {
        model.addAttribute("muebleId", id);
        return "admin/variantes-admin";
    }

    // Cotizaciones
    @GetMapping("/admin/cotizaciones")
    public String adminCotizaciones() {
        return "admin/cotizaciones-admin";
    }
}
