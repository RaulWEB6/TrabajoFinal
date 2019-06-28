package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "recurso")
public class Recurso implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRecurso;

	@NotEmpty(message = "Ingresa el nombre del recurso")
	@Column(name = "nombreRecurso", nullable = false, length = 70)
	private String nombreRecurso;

	@Min(2)
	@Max(1500)
	@Column(name = "stockRecurso", nullable = false)
	private int stockRecurso;

	@NotEmpty(message = "Ingresa el tipo de envasado del recurso")
	@Column(name = "envaseRecurso", nullable = false, length = 70)
	private String envaseRecurso;

	@Min(1)
	@Max(25000)
	@Column(name = "precioRecurso", nullable = false)
	private int precioRecurso;

	private String foto;

	public Recurso() {
		super();
	}

	public Recurso(int idRecurso, String nombreRecurso, int stockRecurso, String envaseRecurso, int precioRecurso,
			String foto) {
		super();
		this.idRecurso = idRecurso;
		this.nombreRecurso = nombreRecurso;
		this.stockRecurso = stockRecurso;
		this.envaseRecurso = envaseRecurso;
		this.precioRecurso = precioRecurso;
		this.foto=foto;
	}

	public int getIdRecurso() {
		return idRecurso;
	}

	public void setIdRecurso(int idRecurso) {
		this.idRecurso = idRecurso;
	}

	public String getNombreRecurso() {
		return nombreRecurso;
	}

	public void setNombreRecurso(String nombreRecurso) {
		this.nombreRecurso = nombreRecurso;
	}

	public int getStockRecurso() {
		return stockRecurso;
	}

	public void setStockRecurso(int stockRecurso) {
		this.stockRecurso = stockRecurso;
	}

	public String getEnvaseRecurso() {
		return envaseRecurso;
	}

	public void setEnvaseRecurso(String envaseRecurso) {
		this.envaseRecurso = envaseRecurso;
	}

	public int getPrecioRecurso() {
		return precioRecurso;
	}

	public void setPrecioRecurso(int precioRecurso) {
		this.precioRecurso = precioRecurso;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}
