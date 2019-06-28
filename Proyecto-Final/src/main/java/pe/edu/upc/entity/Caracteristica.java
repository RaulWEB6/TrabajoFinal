package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "productos")
public class Caracteristica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCaracteristica;

	@NotEmpty(message = "Ingresa el nombre de la caracteristica")
	@Column(name = "nombreCaracteristica", nullable = false, length = 45)
	private String nombreCaracteristica;

	@ManyToOne
	@JoinColumn(name = "idRecurso")
	private Recurso recursocaracteristica;

	public Caracteristica() {
		super();
	}

	public Caracteristica(int idCaracteristica, String nombreCaracteristica, Recurso recursocaracteristica) {
		super();
		this.idCaracteristica = idCaracteristica;
		this.nombreCaracteristica = nombreCaracteristica;
		this.recursocaracteristica = recursocaracteristica;
	}

	public int getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(int idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public String getNombreCaracteristica() {
		return nombreCaracteristica;
	}

	public void setNombreCaracteristica(String nombreCaracteristica) {
		this.nombreCaracteristica = nombreCaracteristica;
	}

	public Recurso getRecursocaracteristica() {
		return recursocaracteristica;
	}

	public void setRecursocaracteristica(Recurso recursocaracteristica) {
		this.recursocaracteristica = recursocaracteristica;
	}
}
