package com.muebleria.service.estrategia;

public class PrecioConVariante implements PrecioStrategy {

    @Override
    public double calcularPrecio(double precioBase, double extra) {
        return precioBase + extra;
    }
}
