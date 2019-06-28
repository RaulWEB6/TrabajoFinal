package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Detalle_orden;
import pe.edu.upc.repository.DetalleRepository;
import pe.edu.upc.service.IDetalleService;

@Service
public class DetalleServiceImpl implements IDetalleService{

	@Autowired
	private DetalleRepository dR;
	
	@Override
	@Transactional
	public void insertar(Detalle_orden detalle) {
		dR.save(detalle);
	}

	@Override
	@Transactional
	public void modificar(Detalle_orden detalle) {
		dR.save(detalle);
	}

	@Override
	@Transactional
	public void eliminar(int idDetalle) {
		dR.deleteById(idDetalle);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Detalle_orden> listarId(int idDetalle) {
		return dR.findById(idDetalle);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Detalle_orden> listar() {
		return dR.findAll();
	}

	@Override
	public List<Detalle_orden> buscar(int idDetalle) {
		return dR.findByIdDetalle(idDetalle);
	}

	@Override
	public List<Detalle_orden> buscarOrden(int idOrden) {
		return dR.buscarOrden(idOrden);
	}

	@Override
	public List<Detalle_orden> buscarRecurso(String nombreRecurso) {
		return dR.buscarRecurso(nombreRecurso);
	}

}
