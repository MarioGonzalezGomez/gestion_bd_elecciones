package com.mggcode.gestion_bd_elecciones.service.municipales;

import com.mggcode.gestion_bd_elecciones.model.municipales.Partido;
import com.mggcode.gestion_bd_elecciones.repository.municipales.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoService implements IBaseService<Partido> {

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public Partido create(Partido partido) {
        return partidoRepository.save(partido);
    }

    @Override
    public Partido update(Partido partido) {
        return partidoRepository.save(partido);
    }

    @Override
    public Partido findById(String id) {
        Optional<Partido> partidoOptional = partidoRepository.findById(id);
        return partidoOptional.orElse(null);
    }

    @Override
    public List<Partido> findAll() {
        return partidoRepository.findAll();
    }

    @Override
    public void delete(String id) {
        partidoRepository.deleteById(id);
    }
}
