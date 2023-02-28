package com.mggcode.gestion_bd_elecciones.service;

import com.mggcode.gestion_bd_elecciones.model.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.repository.CircunscripcionPartidoRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CsvExportService {

    @Autowired
    private CircunscripcionPartidoRepository circunscripcionPartidoRepository;

    public void writeCPToCsv(List<CircunscripcionPartido> cp, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("Cod Circunscripcion", "Cod Partido", "Escanios_desde", "Escanios_hasta", "Porcentaje Voto",
                    "Votantes", "Escanios_desde_historico", "Escanios_hasta_historico", "Votantes historico", "Escanios_desde_sondeo",
                    "Escanios_hasta_sondeo", "Porcentaje Voto Sondeo"
            );
            for (CircunscripcionPartido cirPar : cp) {
                csvPrinter.printRecord(cirPar.getKey().getCircunscripcion(), cirPar.getKey().getPartido(),
                        cirPar.getEscanos_desde(), cirPar.getEscanos_hasta(), cirPar.getPorcentajeVoto(),
                        cirPar.getNumVotantes(), cirPar.getEscanos_desde_hist(), cirPar.getEscanos_hasta_hist(),
                        cirPar.getVotantesHistorico(), cirPar.getEscanos_desde_sondeo(), cirPar.getEscanos_hasta_sondeo(),
                        cirPar.getPorcentajeVotoSondeo()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
