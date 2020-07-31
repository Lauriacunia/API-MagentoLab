package ar.com.ada.api.magnetolab.services;

import ar.com.ada.api.magnetolab.entities.*;
import ar.com.ada.api.magnetolab.repos.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MuestraService {
	@Autowired
    MuestraRepo muestrarepo;

    public static boolean esMutante(String[] adn) {

        int secuenciasRepetidas = 0;
        
		for (int f = 0; f < adn.length; f++) {  // recorro filas

			for (int c = 0; c < adn.length; c++) {  // recorro columnas
    
				if (c - 1 + 4 < adn.length) { // Busco hacia la derecha horizontal
					if (adn[f].charAt(c) == adn[f].charAt(c + 1) && adn[f].charAt(c) == adn[f].charAt(c + 2)
							&& adn[f].charAt(c) == adn[f].charAt(c + 3)) {

						secuenciasRepetidas++;
					}
				}
				if (f - 1 + 4 < adn.length && c + 1 - 4 >= 0) { // Busco hacia la izquierda diagonal
					if (adn[f].charAt(c) == adn[f + 1].charAt(c - 1) && adn[f].charAt(c) == adn[f + 2].charAt(c - 2)
							&& adn[f].charAt(c) == adn[f + 3].charAt(c - 3)) {
						secuenciasRepetidas++;
					}
				}
				if (f - 1 + 4 < adn.length && c - 1 + 4 < adn.length) { // Busco hacia la derecha diagonal
					if (adn[f].charAt(c) == adn[f + 1].charAt(c + 1) && adn[f].charAt(c) == adn[f + 2].charAt(c + 2)
							&& adn[f].charAt(c) == adn[f + 3].charAt(c + 3)) {
						secuenciasRepetidas++;
					}
				}
				if (f - 1 + 4 < adn.length) { // Busco en columnas
							
					if (adn[f].charAt(c) == adn[f + 1].charAt(c) && adn[f].charAt(c) == adn[f + 2].charAt(c)
							&& adn[f].charAt(c) == adn[f + 3].charAt(c)) {
						secuenciasRepetidas++;
					}
				}
             
			}
		}

		return secuenciasRepetidas > 1; // devuelvo true si hay mas de una secuencia repetida
	}

    public void grabar(Muestra m){
        muestrarepo.save(m);
    }
    

    public List<Muestra> listarMuestras() {

        return muestrarepo.findAll();
	}
	
	public List<Muestra> listarMuestrasPorCategoria(int cat){

		List<Muestra> m = new ArrayList();
		m = muestrarepo.findAllByCategoria(cat);
		return m; 

	}
	
	public int contarMutantes(){

		int m = muestrarepo.contar(1);
		return m;

	}

	public int contarHumanos(){
		
		int h = muestrarepo.contar(0);
		return h;

	}
	public String convertirToString(String[] adn){

		String cadena = "";
		
		for (int f=0; f < adn.length; f++){
			cadena = cadena + adn[f] + ",";
		 }

		return cadena;

	}

	public String[] convertirToArray(String adnS){
		String[] adn = new String[6];
		return adn;
	}
}