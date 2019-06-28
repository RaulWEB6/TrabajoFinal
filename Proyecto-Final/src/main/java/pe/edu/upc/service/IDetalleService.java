package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Detalle_orden;


public interface IDetalleService {

	public void insertar(Detalle_orden detalle);

	public void modificar(Detalle_orden detalle);

	public void eliminar(int idDetalle);

	Optional<Detalle_orden> listarId(int idDetalle);

	List<Detalle_orden> listar();

	List<Detalle_orden> buscar(int idDetalle);

	List<Detalle_orden> buscarOrden(int idOrden);

	List<Detalle_orden> buscarRecurso(String nombreRecurso);
}
