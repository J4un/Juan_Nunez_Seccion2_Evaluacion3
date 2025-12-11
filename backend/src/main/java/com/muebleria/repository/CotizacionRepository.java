package com.muebleria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.muebleria.entity.Cotizacion;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {
}
