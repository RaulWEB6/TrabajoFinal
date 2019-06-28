package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Caracteristica;


public interface ICaracteristicaService {

	public void insertar(Caracteristica caracteristica);
	public void modificar(Caracteristica caracteristica);
	public void eliminar(int idCaracteristica);
	Optional<Caracteristica> listarId(int idCaracteristica);
	List<Caracteristica> listar();
	List<Caracteristica> buscar(String nombreCaracteristica);
	List<Caracteristica> buscarRecurso(String nombreRecurso);
}
