package com.muebleria.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrecioServiceTest {

    private final PrecioService precioService = new PrecioService();

    //testeo de las operaciones basicas
    //
    @Test
    public void testPrecioNormal() {
        double precio = precioService.calcularPrecio(35000, 0.0);
        assertEquals(35000, precio, "Precio normal debe ser igual al base");
    }

    @Test
    public void testPrecioConVariante() {
        double precio = precioService.calcularPrecio(35000, 5000.0);
        assertEquals(40000, precio, "Precio con variante debe sumarle el extra");
    }
}
