package com.muebleria.service;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.muebleria.entity.Mueble;
import com.muebleria.repository.MuebleRepository;




@Service
public class MuebleService {

    private final MuebleRepository repository;

    public MuebleService(MuebleRepository repository) {
        this.repository = repository;
    }

    public List<Mueble> listar() {
        return repository.findAll();
    }
    public Optional<Mueble> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Mueble crear(Mueble mueble) {
        mueble.setEstado("activo");
        return repository.save(mueble);
    }


    public Mueble actualizar(Long id, Mueble nuevo) {
        return repository.findById(id).map(m -> {
            m.setNombre(nuevo.getNombre());
            m.setTipo(nuevo.getTipo());
            m.setPrecioBase(nuevo.getPrecioBase());
            m.setStock(nuevo.getStock());
            m.setTamano(nuevo.getTamano());
            m.setMaterial(nuevo.getMaterial());
            return repository.save(m);
        }).orElse(null);
    }



    /*public void desactivar(Long id) {
        repository.findById(id).ifPresent(m -> {
           m.setEstado("inactivo");});
    }

     */
    public void desactivar(Long id) {
        repository.findById(id).ifPresent(m -> {
            m.setEstado("inactivo");
            repository.save(m);
        });
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
