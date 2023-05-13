package com.mggcode.gestion_bd_elecciones.mapper.autonomicas;

import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CarmenDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CircunscripcionDTO;
import com.mggcode.gestion_bd_elecciones.DTO.autonomicas.CpDTO;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Circunscripcion;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.CircunscripcionPartido;
import com.mggcode.gestion_bd_elecciones.model.autonomicas.Partido;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CarmenDTOMapper {
    private final int gradosTotales = 180;
    private ArrayList<Double> posicionesIniciales;
    private ArrayList<Double> posicionesFinales;
    private ArrayList<Double> sumatorios;

    public CarmenDTO toDTO(Circunscripcion c, Circunscripcion espania, List<CircunscripcionPartido> cp, List<Partido> p) {
        CircunscripcionDTOMapper mapper = new CircunscripcionDTOMapper();
        CircunscripcionDTO cdto = mapper.toDTO(c, espania, "Participación", getAnio(c.getCodigoComunidad()));
        posicionesIniciales = new ArrayList<>();
        posicionesIniciales.add(0.0);
        posicionesIniciales.add(0.0);
        posicionesIniciales.add(0.0);
        posicionesFinales = new ArrayList<>();
        posicionesFinales.add(0.0);
        posicionesFinales.add(0.0);
        posicionesFinales.add(0.0);
        sumatorios = getSumatorios(cp);

        List<CpDTO> cpDTO = new ArrayList<>();
        cp.forEach(x -> {
            Partido pTemp = p.stream().filter(y -> y.getCodigo().equals(x.getKey().getPartido())).toList().get(0);
            ArrayList<Double> aperturas = getAperturasArco(x);
            CpDTO cpTemp = CpDTO.builder()
                    .escanos_desde(x.getEscanos_desde())
                    .escanos_hasta(x.getEscanos_hasta())
                    .escanos_hasta_hist(x.getEscanos_hasta_hist())
                    .numVotantes(x.getNumVotantes())
                    .porcentajeVoto(x.getPorcentajeVoto())
                    .porcentajeVotoHistorico(x.getVotantesHistorico())
                    .siglas(pTemp.getSiglas())
                    .literalPartido(pTemp.getNombreCompleto())
                    .codigoPartido(pTemp.getCodigo())
                    .codigoPadre(pTemp.getCodigoPadre())
                    .escanos_desde_sondeo(x.getEscanos_desde_sondeo())
                    .escanos_hasta_sondeo(x.getEscanos_hasta_sondeo())
                    .porcentajeVotoSondeo(x.getPorcentajeVotoSondeo())
                    .posicionInicial(posicionesIniciales.get(0))
                    .aperturaArco(aperturas.get(0))
                    .posicionInicialDesdeSondeo(posicionesIniciales.get(1))
                    .aperturaArcoDesdeSondeo(aperturas.get(1))
                    .posicionInicialHastaSondeo(posicionesIniciales.get(2))
                    .aperturaArcoHastaSondeo(aperturas.get(2))
                    .build();
            cpDTO.add(cpTemp);
            posicionesIniciales.clear();
            posicionesIniciales.addAll(posicionesFinales);
        });

        return CarmenDTO.builder()
                .circunscripcion(cdto)
                .numPartidos(cp.size())
                .cpDTO(cpDTO)
                .build();
    }

    private String getAnio(String codigoAutonomia) {
        String anio;
        if (Objects.equals(codigoAutonomia, "12"))
            anio = "2021";
        else
            anio = "2019";
        return anio;

    }

    private ArrayList<Double> getSumatorios(List<CircunscripcionPartido> cps) {
        ArrayList<Double> sumatorios = new ArrayList<>();
        double sumatorioHasta = cps.stream().mapToInt(CircunscripcionPartido::getEscanos_hasta).sum();
        // double sumatorioDesdeSondeo = cps.stream().mapToInt(CircunscripcionPartido::getEscanos_desde_sondeo).sum();
        double sumatorioHastaSondeo = cps.stream().mapToInt(CircunscripcionPartido::getEscanos_hasta_sondeo).sum();
        sumatorios.add(sumatorioHasta);
        //Se añade una segunda vez, ya que la apertura de los "Desde" tomará también como total el sumatorio de los hasta
        sumatorios.add(sumatorioHastaSondeo);
        //sumatorios.add(sumatorioDesdeSondeo);
        sumatorios.add(sumatorioHastaSondeo);

        return sumatorios;
    }

    private ArrayList<Double> getAperturasArco(CircunscripcionPartido cp) {
        ArrayList<Double> aperturas = new ArrayList<>();
        double aperturaOficial = 0.0;
        double aperturaDesdeSondeo = 0.0;
        double aperturaHastaSondeo = 0.0;
        if (sumatorios.get(0) != 0.0)
            aperturaOficial = cp.getEscanos_hasta() * gradosTotales / sumatorios.get(0);
        if (sumatorios.get(1) != 0.0)
            aperturaDesdeSondeo = cp.getEscanos_desde_sondeo() * gradosTotales / sumatorios.get(1);
        if (sumatorios.get(2) != 0.0)
            aperturaHastaSondeo = cp.getEscanos_hasta_sondeo() * gradosTotales / sumatorios.get(2);

        posicionesFinales.set(0, posicionesIniciales.get(0) + aperturaOficial);
        posicionesFinales.set(1, posicionesIniciales.get(1) + aperturaDesdeSondeo);
        posicionesFinales.set(2, posicionesIniciales.get(2) + aperturaHastaSondeo);
        aperturas.add(aperturaOficial);
        aperturas.add(aperturaDesdeSondeo);
        aperturas.add(aperturaHastaSondeo);
        return aperturas;
    }
}
