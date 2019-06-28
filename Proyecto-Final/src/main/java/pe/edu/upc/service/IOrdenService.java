package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Orden_compra;


public interface IOrdenService {
	
	public void insertar(Orden_compra orden);

	public void modificar(Orden_compra orden);

	public void eliminar(int idOrden);

	Optional<Orden_compra> listarId(int idOrden);

	List<Orden_compra> listar();
	
	List<Orden_compra> buscar(String notaOrden);

	List<Orden_compra> buscarTrabajador(String nombreTrabajador);

	List<Orden_compra> buscarProveedor(String nombreProveedor);

}
