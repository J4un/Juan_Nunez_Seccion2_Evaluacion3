package com.muebleria.entity;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;




@Entity
@Table(name = "detalle_cotizacion")

public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;

    private double subtotal;

    /////////////joins para determinar variaciones de un mueble
    @ManyToOne
    @JoinColumn(name = "mueble_id")
    private Mueble mueble;

    @ManyToOne
    @JoinColumn(name = "variante_id", nullable = true)
    private Variante variante;

    @ManyToOne
    @JoinColumn(name = "cotizacion_id")
    @JsonBackReference


    private Cotizacion cotizacion;






    public DetalleCotizacion() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public Mueble getMueble() { return mueble; }
    public void setMueble(Mueble mueble) { this.mueble = mueble; }

    public Variante getVariante() { return variante; }
    public void setVariante(Variante variante) { this.variante = variante; }

    public Cotizacion getCotizacion() { return cotizacion; }
    public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }





}
