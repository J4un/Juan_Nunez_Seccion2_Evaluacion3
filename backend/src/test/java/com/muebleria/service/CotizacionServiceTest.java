package com.muebleria.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

import com.muebleria.entity.*;
import com.muebleria.repository.*;

import java.util.List;

public class CotizacionServiceTest {

    private CotizacionService cotizacionService;
    private MuebleRepository muebleRepository;
    private VarianteRepository varianteRepository;
    private CotizacionRepository cotizacionRepository;
    private DetalleCotizacionRepository detalleRepository;

    @BeforeEach
    void setup() {
        muebleRepository = Mockito.mock(MuebleRepository.class);
        varianteRepository = Mockito.mock(VarianteRepository.class);
        cotizacionRepository = Mockito.mock(CotizacionRepository.class);
        detalleRepository = Mockito.mock(DetalleCotizacionRepository.class);

        cotizacionService = new CotizacionService(
                cotizacionRepository,
                detalleRepository,
                muebleRepository,
                varianteRepository,
                new PrecioService()
        );
    }

    @Test
    void testVentaSinStockDebeFallar() {
        // Simula un mueble con poco stock
        Mueble mueble = new Mueble();
        mueble.setId(1L);
        mueble.setNombre("Silla comedor");
        mueble.setPrecioBase(35000);
        mueble.setStock(0);

        DetalleCotizacion detalle = new DetalleCotizacion();
        detalle.setMueble(mueble);
        detalle.setCantidad(2);

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(1L);
        cotizacion.setDetalles(List.of(detalle));

        // Mock: cuando busque el mueble, devolverlo
        Mockito.when(cotizacionRepository.findById(1L)).thenReturn(Optional.of(cotizacion));
        Mockito.when(muebleRepository.findById(1L)).thenReturn(Optional.of(mueble));

        Exception ex = assertThrows(Exception.class, () -> {
            cotizacionService.confirmarVenta(1L);
        });

        assertTrue(ex.getMessage().contains("Stock insuficiente"));
    }
}
