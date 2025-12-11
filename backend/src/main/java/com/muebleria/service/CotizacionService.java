package com.muebleria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.muebleria.entity.*;
import com.muebleria.repository.*;

@Service
public class CotizacionService {

    private final CotizacionRepository cotizacionRepository;
    private final DetalleCotizacionRepository detalleRepository;
    private final MuebleRepository muebleRepository;
    private final VarianteRepository varianteRepository;
    private final PrecioService precioService;

    public CotizacionService(CotizacionRepository cotizacionRepository,
                             DetalleCotizacionRepository detalleRepository,
                             MuebleRepository muebleRepository,
                             VarianteRepository varianteRepository,
                             PrecioService precioService) {
        this.cotizacionRepository = cotizacionRepository;
        this.detalleRepository = detalleRepository;
        this.muebleRepository = muebleRepository;
        this.varianteRepository = varianteRepository;
        this.precioService = precioService;
    }

    public Cotizacion crearCotizacion(List<DetalleCotizacion> detalles) {
        Cotizacion cotizacion = new Cotizacion();
        double total = 0;

        for (DetalleCotizacion d : detalles) {
            Optional<Mueble> muebleOpt = muebleRepository.findById(d.getMueble().getId());
            if (muebleOpt.isEmpty()) continue;

            Mueble mueble = muebleOpt.get();
            double precioBase = mueble.getPrecioBase();
            double extra = 0;

            if (d.getVariante() != null) {
                Optional<Variante> varianteOpt = varianteRepository.findById(d.getVariante().getId());
                if (varianteOpt.isPresent()) {
                    extra = varianteOpt.get().getPrecioExtra();
                    d.setVariante(varianteOpt.get());
                }
            }

            double precioFinal = precioService.calcularPrecio(precioBase, extra);
            d.setSubtotal(precioFinal * d.getCantidad());
            d.setMueble(mueble);
            d.setCotizacion(cotizacion);
            total += d.getSubtotal();
        }

        cotizacion.setTotal(total);
        cotizacionRepository.save(cotizacion);
        detalleRepository.saveAll(detalles);

        return cotizacion;
    }

    public Cotizacion confirmarVenta(Long idCotizacion) throws Exception {
        Optional<Cotizacion> cotOpt = cotizacionRepository.findById(idCotizacion);
        if (cotOpt.isEmpty()) throw new Exception("Cotización no encontrada");

        Cotizacion cot = cotOpt.get();
        if (cot.isConfirmada()) throw new Exception("Ya fue confirmada");

        for (DetalleCotizacion d : cot.getDetalles()) {
            Mueble m = d.getMueble();
            if (m.getStock() < d.getCantidad()) {
                throw new Exception("Stock insuficiente para el mueble: " + m.getNombre());
            }
            m.setStock(m.getStock() - d.getCantidad());
            muebleRepository.save(m);
        }

        cot.setConfirmada(true);
        return cotizacionRepository.save(cot);
    }

    public List<Cotizacion> listar() {
        return cotizacionRepository.findAll();
    }
}
