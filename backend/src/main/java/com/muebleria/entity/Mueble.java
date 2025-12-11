package com.muebleria.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "mueble")
public class Mueble {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private double precioBase;
    private int stock;
    private String estado; //// determinar si el mueble esta activo o inactivo, posible boolean
    private String tamano;
    private String material;

    public Mueble() {}

    public Mueble(String nombre, String tipo, double precioBase, int stock, String estado, String tamano, String material) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.stock = stock;
        this.estado = estado;
        this.tamano = tamano;
        this.material = material;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTamano() { return tamano; }
    public void setTamano(String tamano) { this.tamano = tamano; }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    @Override
    public String toString() {
        return "Mueble{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", precioBase=" + precioBase +
                ", stock=" + stock +
                ", estado='" + estado + '\'' +
                ", tamano='" + tamano + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}