package com.muebleria.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private double total;
    private boolean confirmada;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DetalleCotizacion> detalles;

    public Cotizacion() {
        this.fecha = LocalDate.now();
        this.confirmada = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public boolean isConfirmada() { return confirmada; }
    public void setConfirmada(boolean confirmada) { this.confirmada = confirmada; }

    public List<DetalleCotizacion> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleCotizacion> detalles) { this.detalles = detalles; }
}
