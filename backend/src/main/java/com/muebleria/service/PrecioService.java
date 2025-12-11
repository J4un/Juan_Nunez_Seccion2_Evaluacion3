package com.muebleria.service;

import org.springframework.stereotype.Service;
import com.muebleria.service.estrategia.*;

@Service
public class PrecioService {


    //DEterminar si tiene o no tiene variantes a las que calcularle una diferencia de precio
    public double calcularPrecio(double precioBase, Double precioExtra) {
        PrecioStrategy estrategia;

        if (precioExtra != null && precioExtra > 0) {
            estrategia = new PrecioConVariante();
        } else {
            estrategia = new PrecioNormal();
        }

        return estrategia.calcularPrecio(precioBase, precioExtra != null ? precioExtra : 0);
    }
}
