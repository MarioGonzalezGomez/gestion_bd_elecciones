package com.mggcode.gestion_bd_elecciones.service.autonomicas;

import java.util.List;

public interface IBaseService<T> {
    T create(T t);
    T update(T t);
    T findById(String id);
    List<T> findAll();
    void delete(String id);
}
