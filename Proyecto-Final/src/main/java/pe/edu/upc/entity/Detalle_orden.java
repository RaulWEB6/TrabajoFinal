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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "detalle")
public class Detalle_orden implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetalle;
	
	@Min(2)
	@Max(1000)
	@Column(name = "unidadesDetalle", nullable = false)
	private int unidadesDetalle;
	
	@Min(2)
	@Max(10000)
	@Column(name = "precioDetalle", nullable = false)
	private int precioDetalle;
	
	@ManyToOne
	@JoinColumn(name = "idRecurso")
	private Recurso recursodetalle;
	
	@ManyToOne
	@JoinColumn(name = "idOrden")
	private Orden_compra ordendetalle;
	
	
	public Detalle_orden() {
		super();
	}

	public Detalle_orden(int idDetalle, int unidadesDetalle, int precioDetalle, Recurso recursodetalle,
			Orden_compra ordendetalle) {
		super();
		this.idDetalle = idDetalle;
		this.unidadesDetalle = unidadesDetalle;
		this.precioDetalle = precioDetalle;
		this.recursodetalle = recursodetalle;
		this.ordendetalle = ordendetalle;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getUnidadesDetalle() {
		return unidadesDetalle;
	}

	public void setUnidadesDetalle(int unidadesDetalle) {
		this.unidadesDetalle = unidadesDetalle;
	}

	public int getPrecioDetalle() {
		return precioDetalle;
	}

	public void setPrecioDetalle(int precioDetalle) {
		this.precioDetalle = precioDetalle;
	}

	public Recurso getRecursodetalle() {
		return recursodetalle;
	}

	public void setRecursodetalle(Recurso recursodetalle) {
		this.recursodetalle = recursodetalle;
	}

	public Orden_compra getOrdendetalle() {
		return ordendetalle;
	}

	public void setOrdendetalle(Orden_compra ordendetalle) {
		this.ordendetalle = ordendetalle;
	}
}
