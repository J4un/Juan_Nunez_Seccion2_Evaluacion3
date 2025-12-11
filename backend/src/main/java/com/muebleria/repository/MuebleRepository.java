package com.muebleria.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.muebleria.entity.Mueble;

public interface MuebleRepository extends JpaRepository<Mueble, Long> {
}
