package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Trabajador;

public interface ITrabajadorService{

	public Integer insertar(Trabajador trabajador);
	public void modificar(Trabajador trabajador);
	public void eliminar(int idTrabajador);
	Optional<Trabajador> listarId(int idTrabajador);
	List<Trabajador> listar();
	List<Trabajador> buscarNombre(String nombreTrabajador);

}
