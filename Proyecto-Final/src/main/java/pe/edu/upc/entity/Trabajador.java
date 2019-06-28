package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "trabajador")
public class Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idTrabajador;

	@NotEmpty(message = "Ingresa el nombre del trabajador")
	@Column(name = "nombreTrabajador", nullable = false, length = 70)
	private String nombreTrabajador;

	@NotEmpty(message = "Ingresa el apellido del trabajador")
	@Column(name = "apellidoTrabajador", nullable = false, length = 70)
	private String apellidoTrabajador;
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingresar DNI")
	@Column(name="",nullable=false,length=45,unique=true)
	private String dniTrabajador;

	public Trabajador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trabajador(int idTrabajador, String nombreTrabajador, String apellidoTrabajador, String foto, String dniTrabajador) {
		super();
		this.idTrabajador = idTrabajador;
		this.nombreTrabajador = nombreTrabajador;
		this.apellidoTrabajador = apellidoTrabajador;
		this.dniTrabajador=dniTrabajador;
	}

	public int getIdTrabajador() {
		return idTrabajador;
	}

	public void setIdTrabajador(int idTrabajador) {
		this.idTrabajador = idTrabajador;
	}

	public String getNombreTrabajador() {
		return nombreTrabajador;
	}

	public void setNombreTrabajador(String nombreTrabajador) {
		this.nombreTrabajador = nombreTrabajador;
	}

	public String getApellidoTrabajador() {
		return apellidoTrabajador;
	}

	public void setApellidoTrabajador(String apellidoTrabajador) {
		this.apellidoTrabajador = apellidoTrabajador;
	}

	public String getDniTrabajador() {
		return dniTrabajador;
	}

	public void setDniTrabajador(String dniTrabajador) {
		this.dniTrabajador = dniTrabajador;
	}
}
