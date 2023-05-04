package com.mggcode.gestion_bd_elecciones.service.autonomicas;


import com.mggcode.gestion_bd_elecciones.model.autonomicas.Literal;
import com.mggcode.gestion_bd_elecciones.repository.autonomicas.ALiteralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ALiteralService implements IBaseService<Literal> {

    @Autowired
    private ALiteralRepository literalRepository;

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
