package com.muebleria.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import com.muebleria.entity.Mueble;
import com.muebleria.repository.MuebleRepository;

public class MuebleServiceTest {

    private MuebleService muebleService;
    private MuebleRepository muebleRepository;

    @BeforeEach
    void setup() {
        muebleRepository = Mockito.mock(MuebleRepository.class);
        muebleService = new MuebleService(muebleRepository);
    }

    //da correcto si logra actualizar los datos en un mueble ya existente y lo devuelve sin problemas
    @Test
    void testActualizarMueble() {
        Mueble original = new Mueble("Silla", "Silla", 30000, 5, "activo", "Mediano", "Madera");
        original.setId(1L);
        Mueble nuevo = new Mueble("Silla moderna", "Silla", 35000, 8, "activo", "Grande", "Metal");

        Mockito.when(muebleRepository.findById(1L)).thenReturn(Optional.of(original));
        Mockito.when(muebleRepository.save(Mockito.any(Mueble.class))).thenAnswer(i -> i.getArgument(0));

        Mueble actualizado = muebleService.actualizar(1L, nuevo);

        assertNotNull(actualizado);
        assertEquals("Silla moderna", actualizado.getNombre());
        assertEquals(8, actualizado.getStock());
    }
}
