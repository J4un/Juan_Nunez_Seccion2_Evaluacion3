package com.muebleria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.muebleria.entity.Variante;
import com.muebleria.entity.Mueble;
import com.muebleria.repository.VarianteRepository;
import com.muebleria.repository.MuebleRepository;

@Service
public class VarianteService {

    private final VarianteRepository varianteRepository;
    private final MuebleRepository muebleRepository;



    public VarianteService(VarianteRepository varianteRepository, MuebleRepository muebleRepository) {
        this.varianteRepository = varianteRepository;
        this.muebleRepository = muebleRepository;
    }

    public Variante crear(Long muebleId, Variante variante) {
        Optional<Mueble> muebleOpt = muebleRepository.findById(muebleId);
        if (muebleOpt.isPresent()) {
            variante.setMueble(muebleOpt.get());
            return varianteRepository.save(variante);
        }
        return null;
    }

    public List<Variante> listarPorMueble(Long muebleId) {
        return varianteRepository.findByMuebleId(muebleId);
    }


}
