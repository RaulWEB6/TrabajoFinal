package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Caracteristica;
import pe.edu.upc.repository.CaracteristicaRepository;
import pe.edu.upc.service.ICaracteristicaService;

@Service
public class CaracteristicaServiceImpl implements ICaracteristicaService {

	@Autowired
	private CaracteristicaRepository cR;

	@Override
	@Transactional
	public void insertar(Caracteristica caracteristica) {
		cR.save(caracteristica);
	}

	@Override
	@Transactional
	public void modificar(Caracteristica caracteristica) {
		cR.save(caracteristica);
	}

	@Override
	@Transactional
	public void eliminar(int idCaracteristica) {
		cR.deleteById(idCaracteristica);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Caracteristica> listarId(int idCaracteristica) {
		return cR.findById(idCaracteristica);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Caracteristica> listar() {
		return cR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Caracteristica> buscar(String nombreCaracteristica) {
		return cR.findByNombreCaracteristica(nombreCaracteristica);
	}

	@Override
	public List<Caracteristica> buscarRecurso(String nombreRecurso) {
		return cR.buscarRecurso(nombreRecurso);
	}

	
}
