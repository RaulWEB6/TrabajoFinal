package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "gerente")
public class Gerente extends Trabajador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String distritoGerente;

	public Gerente(int idTrabajador, String nombreTrabajador, String apellidoTrabajador,String dniTrabajador, String distritoGerente) {
		super();
		this.distritoGerente = distritoGerente;
	}

	public Gerente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gerente(int idTrabajador, String nombreTrabajador, String apellidoTrabajador,String dniTrabajador) {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public String getDistritoGerente() {
		return distritoGerente;
	}

	public void setDistritoGerente(String distritoGerente) {
		this.distritoGerente = distritoGerente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
