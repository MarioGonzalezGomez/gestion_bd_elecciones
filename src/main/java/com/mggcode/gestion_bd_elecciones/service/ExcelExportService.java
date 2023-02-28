package com.mggcode.gestion_bd_elecciones.service;

import com.mggcode.gestion_bd_elecciones.repository.CircunscripcionPartidoRepository;
import com.mggcode.gestion_bd_elecciones.repository.CircunscripcionRepository;
import com.mggcode.gestion_bd_elecciones.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcelExportService {

    @Autowired
    private CircunscripcionPartidoRepository circunscripcionPartidoRepository;

    @Autowired
    private PartidoRepository partidoRepository;

    @Autowired
    private CircunscripcionRepository circunscripcionRepository;



}
