package com.muebleria.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.muebleria.entity.Mueble;
import com.muebleria.service.MuebleService;

@RestController
@RequestMapping("/api/muebles")
@CrossOrigin(origins = "*")
public class MuebleController {

    private final MuebleService service;

    public MuebleController(MuebleService service) {
        this.service = service;
    }

    @PostMapping
    public Mueble crear(@RequestBody Mueble mueble) {
        return service.crear(mueble);
    }

    @GetMapping
    public List<Mueble> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Mueble obtener(@PathVariable Long id) {
        return service.buscarPorId(id).orElse(null);
    }



    @PutMapping("/{id}")
    public Mueble actualizar(@PathVariable Long id, @RequestBody Mueble mueble) {
        return service.actualizar(id, mueble);
    }

    @PutMapping("/{id}/desactivar")
    public void desactivar(@PathVariable Long id) {
        service.desactivar(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
