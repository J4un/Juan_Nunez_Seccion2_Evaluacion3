package com.muebleria.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.muebleria.entity.Variante;

public interface VarianteRepository extends JpaRepository<Variante, Long> {
    List<Variante> findByMuebleId(Long muebleId);
}
