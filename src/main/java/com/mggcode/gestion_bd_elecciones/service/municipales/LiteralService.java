package com.mggcode.gestion_bd_elecciones.service.municipales;


import com.mggcode.gestion_bd_elecciones.model.municipales.Literal;
import com.mggcode.gestion_bd_elecciones.repository.municipales.LiteralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiteralService implements IBaseService<Literal> {

    @Autowired
    private LiteralRepository literalRepository;

    @Override
    public Literal create(Literal literal) {
        return literalRepository.save(literal);
    }

    @Override
    public Literal update(Literal literal) {
        return literalRepository.save(literal);
    }

    @Override
    public Literal findById(String id) {
        Optional<Literal> literalOptional = literalRepository.findById(id);
        return literalOptional.orElse(null);
    }

    @Override
    public List<Literal> findAll() {
        return literalRepository.findAll();
    }

    @Override
    public void delete(String id) {
        literalRepository.deleteById(id);
    }
}
