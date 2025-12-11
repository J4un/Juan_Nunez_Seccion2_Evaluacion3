package com.muebleria.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "variante")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precioExtra;



    @ManyToOne
    @JoinColumn(name = "mueble_id")
    private Mueble mueble;

    public Variante() {

    }

    public Variante(String nombre, double precioExtra, Mueble mueble) {
        this.nombre = nombre;
        this.precioExtra = precioExtra;
        this.mueble = mueble;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }



    public String getNombre() {
        return nombre;

    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }


    public double getPrecioExtra() {
        return precioExtra;
    }
    public void setPrecioExtra(double precioExtra) {
        this.precioExtra = precioExtra;
    }


    public Mueble getMueble() {
        return mueble;
    }
    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }




}
