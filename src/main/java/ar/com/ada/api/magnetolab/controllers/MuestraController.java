package ar.com.ada.api.magnetolab.controllers;

import ar.com.ada.api.magnetolab.entities.*;
import ar.com.ada.api.magnetolab.services.*;
import ar.com.ada.api.magnetolab.models.response.*;
import ar.com.ada.api.magnetolab.models.request.*;


import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;


@RestController
public class MuestraController {
    
    @Autowired
    MuestraService muestraService;

    // Recibe la muestra de adn a analizar

    @PostMapping("/muestras")

    public MutanteResponse postMuestra (@RequestBody CandidatoRequest cr) {
        
        MutanteResponse mr = new MutanteResponse();
        Muestra m = new Muestra();
        m.setNombre(cr.nombre);
        m.setAdn(muestraService.convertirToString(cr.adn));

        if (muestraService.esMutante(cr.adn)) {
            m.setCategoria(1);
            mr.isMutant = true;
            mr.message = "Se catalogó como mutante";

        } else {
           
            m.setCategoria(0);
            mr.isMutant = false;
            mr.message = "Se catalogo como humano";

        }
        muestraService.grabar(m);

        return mr;
    }

    //Lista todas las muestras analizadas

    @GetMapping("/muestras")
    public ResponseEntity<List<Muestra>> listarMuestras() {
        
        return ResponseEntity.ok(muestraService.listarMuestras());
 
    }

    //Lista las muestras de categoria 1 (mutantes) o categoria 0 (humanos)
    @GetMapping("/muestras/{cat}")
    public ResponseEntity<List<Muestra>> listarMuestrasPorCategoria(@PathVariable int cat) {
        
        return ResponseEntity.ok(muestraService.listarMuestrasPorCategoria(cat));
 
    }

    // Devuelve informe de estado con:
    // -cantidad de mutantes
    // - cantidad de humanos
    // - radio (cantidad de mutantes/cantidad de humanos)

    @GetMapping("/stats")
    public StatsResponse getStatsResponse() {
        StatsResponse st = new StatsResponse();
        st.count_mutant_dna = muestraService.contarMutantes();
        st.count_human_dna = muestraService.contarHumanos();
        st.ratio = st.count_mutant_dna*1.0d/ st.count_human_dna;
        return st;
    }
}

