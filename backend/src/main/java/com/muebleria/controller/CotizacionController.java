package com.muebleria.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.muebleria.entity.Cotizacion;
import com.muebleria.entity.DetalleCotizacion;
import com.muebleria.service.CotizacionService;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionController {

    private final CotizacionService service;

    public CotizacionController(CotizacionService service) {
        this.service = service;
    }


    @PostMapping
    public Cotizacion crear(@RequestBody List<DetalleCotizacion> detalles) {
        return service.crearCotizacion(detalles);
    }

    @PutMapping("/{id}/confirmar")
    public Cotizacion confirmar(@PathVariable Long id) throws Exception {
        return service.confirmarVenta(id);
    }

    @GetMapping
    public List<Cotizacion> listar() {
        return service.listar();
    }

    ///////Posible eliminar?////////

}
