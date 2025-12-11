package com.muebleria.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.muebleria.entity.Variante;
import com.muebleria.service.VarianteService;

@RestController
@RequestMapping("/api/variantes")
@CrossOrigin(origins = "*")
public class VarianteController {

    private final VarianteService service;

    public VarianteController(VarianteService service) {
        this.service = service;
    }
    //
    /*
    -
     */

    @GetMapping("/mueble/{muebleId}")
    public List<Variante> listarPorMueble(@PathVariable Long muebleId) {
        return service.listarPorMueble(muebleId);
    }

    @PostMapping("/mueble/{muebleId}")
    public Variante crear(@PathVariable Long muebleId, @RequestBody Variante variante) {
        return service.crear(muebleId, variante);
    }
}
