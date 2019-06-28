package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.entity.Trabajador;
import pe.edu.upc.repository.TrabajadorRepository;
import pe.edu.upc.service.ITrabajadorService;

@Service
public class TrabajadorServiceImpl implements ITrabajadorService {

	@Autowired
	private TrabajadorRepository tR;

	@Override
	@Transactional
	public Integer insertar(Trabajador trabajador) {
		int rpta = tR.buscarDniTrabajador(trabajador.getDniTrabajador());
		if (rpta == 0) {
			tR.save(trabajador);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void modificar(Trabajador trabajador) {
		tR.save(trabajador);
	}

	@Override
	@Transactional
	public void eliminar(int idTrabajador) {
		tR.deleteById(idTrabajador);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Trabajador> listarId(int idTrabajador) {
		return tR.findById(idTrabajador);
	}

	@Override
	public List<Trabajador> listar() {
		return tR.findAll();
	}

	@Override
	public List<Trabajador> buscarNombre(String nombreTrabajador) {
		return tR.findByNombreTrabajador(nombreTrabajador);
	}
}
