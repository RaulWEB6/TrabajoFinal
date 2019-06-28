package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "orden") // productos
public class Orden_compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrden;

	@NotNull(message = "La fecha es obligatoria")
	@Past(message = "La fecha debe estar en el pasado")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaOrden")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaOrden;

	@NotEmpty(message = "Ingresa la nota de la orden")
	@Column(name = "notaOrden", nullable = false, length = 70)
	private String notaOrden;
	
	@ManyToOne
	@JoinColumn(name = "idProveedor")
	private Proveedor proveedororden;

	@ManyToOne
	@JoinColumn(name = "idTrabajador")
	private Trabajador trabajadororden;
	
	public Orden_compra() {
		super();
	}

	public Orden_compra(int idOrden, Date fechaOrden, Proveedor proveedororden, Trabajador trabajadororden,String notaOrden) {
		super();
		this.idOrden = idOrden;
		this.fechaOrden = fechaOrden;
		this.proveedororden = proveedororden;
		this.trabajadororden = trabajadororden;
		this.notaOrden=notaOrden;
	}

	public int getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(int idOrden) {
		this.idOrden = idOrden;
	}

	public Date getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(Date fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public Proveedor getProveedororden() {
		return proveedororden;
	}

	public void setProveedororden(Proveedor proveedororden) {
		this.proveedororden = proveedororden;
	}

	public Trabajador getTrabajadororden() {
		return trabajadororden;
	}

	public void setTrabajadororden(Trabajador trabajadororden) {
		this.trabajadororden = trabajadororden;
	}

	public String getNotaOrden() {
		return notaOrden;
	}

	public void setNotaOrden(String notaOrden) {
		this.notaOrden = notaOrden;
	}
}
