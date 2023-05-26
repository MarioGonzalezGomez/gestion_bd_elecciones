package com.mggcode.gestion_bd_elecciones.service.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.*;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
        DecimalFormat df = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        df.setDecimalFormatSymbols(dfs);
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord("Codigo", "Comunidad Autonoma", "Provincia", "Municipio", "Descripcion", "Escrutado", "Escanios",
                    "Participacion", "Participacion Historica", "Media de participacion", "Avance", "Votantes",
                    "Escanios Historicos", "Anio ultimas elecciones", "Mayoria", "Sin uso", "Sin uso",
                    "Numero de partidos", "Tipo de elecciones"
            );
            CircunscripcionDTO cir = cDTO.getCircunscripcion();
            csvPrinter.printRecord(cir.getCodigo(), cir.getCodigoComunidad(), cir.getCodigoProvincia(), cir.getCodigoMunicipio(),
                    cir.getNombreCircunscripcion(), cir.getEscrutado(), cir.getEscanios(),
                    cir.getParticipacion(), cir.getParticipacionHistorico(), cir.getParticipacionMedia(), cir.getAvanceActual(), cir.getVotantes(),
                    cir.getEscaniosHistoricos(), cir.getAnioUltimosDatos(), cir.getMayoria(), cir.getAutonomiaOMunicipio(), cir.getParticipacionHist(),
                    cDTO.getNumPartidos(), "1"
            );

            csvPrinter.printRecord("Cod Partido", "Cod Padre", "Escanios_Desde", "Escanios_Dasta",
                    "Escanios_historicos", "Porcentaje Voto",
                    "Porcentaje historico", "Votantes", "Siglas", "Literal", "Posicion Inicial Oficiales", "Apertura Arco Oficiales",
                    "Posicion Inicial Desde Sondeo", "Apertura Arco Desde Sondeo", "Posicion Inicial Hasta Sondeo", "Apertura Arco Hasta Sondeo",
                    "Escanios Desde Sondeo", "Escanios Hasta Sondeo", "Porcentaje Voto Sondeo", "Diferencia de escanios", "Tendencia"
            );

            for (CpDTO dto : cDTO.getCpDTO()) {
                int diferencia = dto.getEscanos_hasta() - dto.getEscanos_hasta_hist();
                String tendencia;
                if (dto.getEscanos_hasta_hist() == 0) {
                    diferencia = 0;
                    tendencia = "*";
                } else if (diferencia < 0) {
                    diferencia = diferencia * (-1);
                    tendencia = "-";
                } else if (diferencia == 0) {
                    tendencia = "=";
                } else {
                    tendencia = "+";
                }
                if (diferencia != 0) {
                    csvPrinter.printRecord(dto.getCodigoPartido(), dto.getCodigoPadre(), dto.getEscanos_desde(), dto.getEscanos_hasta(),
                            dto.getEscanos_hasta_hist(), dto.getPorcentajeVoto(),
                            dto.getPorcentajeVotoHistorico(), dto.getNumVotantes(), dto.getSiglas(), dto.getLiteralPartido(),
                            df.format(dto.getPosicionInicial()), df.format(dto.getAperturaArco()), df.format(dto.getPosicionInicialDesdeSondeo()),
                            df.format(dto.getAperturaArcoDesdeSondeo()), df.format(dto.getPosicionInicialHastaSondeo()), df.format(dto.getAperturaArcoHastaSondeo()),
                            dto.getEscanos_desde_sondeo(), dto.getEscanos_hasta_sondeo(), dto.getPorcentajeVotoSondeo(), String.valueOf(diferencia), tendencia
                    );
                } else {
                    csvPrinter.printRecord(dto.getCodigoPartido(), dto.getCodigoPadre(), dto.getEscanos_desde(), dto.getEscanos_hasta(),
                            dto.getEscanos_hasta_hist(), dto.getPorcentajeVoto(),
                            dto.getPorcentajeVotoHistorico(), dto.getNumVotantes(), dto.getSiglas(), dto.getLiteralPartido(),
                            df.format(dto.getPosicionInicial()), df.format(dto.getAperturaArco()), df.format(dto.getPosicionInicialDesdeSondeo()),
                            df.format(dto.getAperturaArcoDesdeSondeo()), df.format(dto.getPosicionInicialHastaSondeo()), df.format(dto.getAperturaArcoHastaSondeo()),
                            dto.getEscanos_desde_sondeo(), dto.getEscanos_hasta_sondeo(), dto.getPorcentajeVotoSondeo(), "", tendencia);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeSedesDTOToCsv(SedesDTO dto, Writer writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            csvPrinter.printRecord("Cod Partido", "Cod Padre", "Escanios_desde", "Escanios_hasta", "Escanios_historico",
                    "Porcentaje Voto", "Porcentaje Voto Historico", "Votantes", "Siglas", "Literal", "Votantes historico"
            );
            csvPrinter.printRecord(dto.getCodigoPartido(), dto.getCodigoPadre(),
                    dto.getEscanos_desde(), dto.getEscanos_hasta(), dto.getEscanos_hist(),
                    dto.getPorcentajeVoto(), dto.getPorcentajeVotoHistorico(), dto.getNumVotantes(),
                    dto.getSiglas(), dto.getLiteralPartido(), dto.getNumVotantes_hist()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeResultadosDTOToCsv(ResultadosDTO dto, PrintWriter writer) {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withDelimiter(';'))) {
            //DATOS CIRCUNSCRIPCION
            csvPrinter.printRecord("Codigo de Autonomía", "Nombre", "Número de provincias");
            CircunscripcionResultadosDTO cir = dto.getCcaa();
            csvPrinter.printRecord(cir.getCodigo(), cir.getNombre(), cir.getNumProvincias());

            //DATOS GANADORES
            csvPrinter.printRecord("Codigo Circunscripcion", "Nombre", "Codigo Partido Ganador", "Siglas");

            for (ProvinciaResultadosDTO provinciaDTO : dto.getProvincias()) {
                csvPrinter.printRecord(provinciaDTO.getCodigo(), provinciaDTO.getNombre(),
                        provinciaDTO.getCodPartidoGanador(), provinciaDTO.getNomPartidoGanador()
                );
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
