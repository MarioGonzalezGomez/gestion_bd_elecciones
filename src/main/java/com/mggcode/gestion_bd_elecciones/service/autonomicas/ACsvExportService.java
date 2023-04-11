package com.mggcode.gestion_bd_elecciones.service.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CpDTO;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class ACsvExportService {

    public void writeCPToCsv(List<CircunscripcionPartido> cp, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
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

    public void writePartidoToCsv(List<Partido> partidos, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord("Codigo", "Siglas", "Codigo padre", "Nombre completo");
            for (Partido par : partidos) {
                csvPrinter.printRecord(par.getCodigo(), par.getSiglas(), par.getCodigoPadre(), par.getNombreCompleto());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeCircunscripcionToCsv(List<Circunscripcion> circunscripciones, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord("Codigo", "Comunidad Autonoma", "Provincia", "Municipio", "Descripcion",
                    "Escrutado", "Escanios", "Avance 1", "Avance 2", "Avance 3", "Participacion", "Votantes",
                    "Escanios Historicos", "Avance 1 Historico", "Avance 2 Historico", "Avance 3 Historico", "Participacion Historica");
            for (Circunscripcion cir : circunscripciones) {
                csvPrinter.printRecord(cir.getCodigo(), cir.getCodigoComunidad(), cir.getCodigoProvincia(), cir.getCodigoMunicipio(),
                        cir.getNombreCircunscripcion(), cir.getEscrutado(), cir.getEscanios(), cir.getAvance1(), cir.getAvance2(),
                        cir.getAvance3(), cir.getParticipacion(), cir.getVotantes(), cir.getEscaniosHistoricos(), cir.getAvance1Hist(),
                        cir.getAvance2Hist(), cir.getAvance3Hist(), cir.getParticipacionHist()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeCarmenDTOToCsv(CarmenDTO cDTO, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord("Codigo", "Descripcion", "Escanios", "Numero de partidos", "Escrutado", "Comunidad Autonoma", "Provincia", "Municipio"
                    //  "Avance 1", "Avance 2", "Avance 3", "Participacion", "Votantes",
                    // "Escanios Historicos", "Avance 1 Historico", "Avance 2 Historico", "Avance 3 Historico", "Participacion Historica"
            );
            Circunscripcion cir = cDTO.getCircunscripcion();
            csvPrinter.printRecord(cir.getCodigo(), cir.getNombreCircunscripcion(), cir.getEscanios(),
                    cDTO.getNumPartidos(), cir.getEscrutado(), cir.getCodigoComunidad(), cir.getCodigoProvincia(), cir.getCodigoMunicipio()
                    //  cir.getAvance1(), cir.getAvance2(),
                    //  cir.getAvance3(), cir.getParticipacion(), cir.getVotantes(), cir.getEscaniosHistoricos(), cir.getAvance1Hist(),
                    //  cir.getAvance2Hist(), cir.getAvance3Hist(), cir.getParticipacionHist()
            );

            csvPrinter.printRecord("Cod Partido", "Cod Padre", "Escanios_desde", "Escanios_hasta",
                    "Escanios_historicos", "Porcentaje Voto",
                    "Porcentaje historico", "Votantes", "Siglas", "Literal"
            );

            for (CpDTO dto : cDTO.getCpDTO()) {
                csvPrinter.printRecord(dto.getCodigoPartido(), dto.getCodigoPadre(), dto.getEscanos_desde(), dto.getEscanos_hasta(),
                        dto.getEscanos_hasta_hist(), dto.getPorcentajeVoto(),
                        dto.getPorcentajeVotoHistorico(), dto.getNumVotantes(), dto.getSiglas(), dto.getLiteralPartido()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
