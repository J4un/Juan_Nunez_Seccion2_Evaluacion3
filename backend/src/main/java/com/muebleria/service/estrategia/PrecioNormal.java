package com.muebleria.service.estrategia;

public class PrecioNormal implements PrecioStrategy {
    @Override
    public double calcularPrecio(double precioBase, double extra) {
        return precioBase;
    }
}
