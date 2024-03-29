package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProveedor;
	
	@NotEmpty(message = "Ingresa el nombre del proveedor")
	@Column(name = "nombreProveedor", nullable = false, length = 70)
	private String nombreProveedor;
	
	@Size(min = 10, max = 10)
	@NotEmpty(message = "Ingresar RUC")
	@Column(name="",nullable=false,length=45,unique=true)
	private String rucProveedor ;
	
	@NotEmpty(message = "Ingresa el nombre de contacto del proveedor")
	@Column(name = "nombrecontactoProveedor", nullable = false, length = 70)
	private String nombrecontactoProveedor;
	
	@Size(min = 9, max = 9)
	@NotEmpty(message = "Ingresar numero de contacto")
	@Column(name = "numerocontactoProveedor", nullable = false, length = 9)
	private String numerocontactoProveedor;
	
	@NotEmpty(message = "Ingresa la direccion del local principal del proveedor")
	@Column(name = "direccionProveedor", nullable = false, length = 70)
	private String direccionProveedor;
	

	public Proveedor() {
		super();
	}
	
	public Proveedor(int idProveedor, String nombreProveedor, String rucProveedor, String nombrecontactoProveedor,
			String numerocontactoProveedor, String direccionProveedor) {
		super();
		this.idProveedor = idProveedor;
		this.nombreProveedor = nombreProveedor;
		this.rucProveedor = rucProveedor;
		this.nombrecontactoProveedor = nombrecontactoProveedor;
		this.numerocontactoProveedor = numerocontactoProveedor;
		this.direccionProveedor = direccionProveedor;
	}


	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getRucProveedor() {
		return rucProveedor;
	}

	public void setRucProveedor(String rucProveedor) {
		this.rucProveedor = rucProveedor;
	}

	public String getNombrecontactoProveedor() {
		return nombrecontactoProveedor;
	}

	public void setNombrecontactoProveedor(String nombrecontactoProveedor) {
		this.nombrecontactoProveedor = nombrecontactoProveedor;
	}

	public String getNumerocontactoProveedor() {
		return numerocontactoProveedor;
	}

	public void setNumerocontactoProveedor(String numerocontactoProveedor) {
		this.numerocontactoProveedor = numerocontactoProveedor;
	}

	public String getDireccionProveedor() {
		return direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}
}
