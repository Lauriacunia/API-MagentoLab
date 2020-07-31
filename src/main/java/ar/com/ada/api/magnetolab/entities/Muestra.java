package ar.com.ada.api.magnetolab.entities;

import ar.com.ada.api.magnetolab.services.*;
import ar.com.ada.api.magnetolab.repos.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "muestra")
public class Muestra {

    @Id
    @Column(name = "muestra_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer muestraId;

	private String nombre;

	private String adn;

    private Integer categoria; // 1 es mutante, 0 es humano

    

	public Integer getMuestraId() {
		return muestraId;
	}

	public void setMuestraId(Integer muestraId) {
		this.muestraId = muestraId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAdn() {
		return adn;
	}

	public void setAdn(String adn) {
		this.adn = adn;
	}

	public Integer getCategoria() {
		return categoria;
	}

	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}

	
    


    }
    
    