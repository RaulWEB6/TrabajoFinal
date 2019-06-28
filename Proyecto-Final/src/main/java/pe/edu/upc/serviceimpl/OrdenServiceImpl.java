package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Orden_compra;
import pe.edu.upc.repository.OrdenRepository;
import pe.edu.upc.service.IOrdenService;

@Service
public class OrdenServiceImpl implements IOrdenService{

	@Autowired
	private OrdenRepository oR;
	
	@Override
	@Transactional
	public void insertar(Orden_compra orden) {
		oR.save(orden);
	}

	@Override
	@Transactional
	public void modificar(Orden_compra orden) {
		oR.save(orden);
	}

	@Override
	@Transactional
	public void eliminar(int idOrden) {
		oR.deleteById(idOrden);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Orden_compra> listarId(int idOrden) {
		return oR.findById(idOrden);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orden_compra> listar() {
		return oR.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Orden_compra> buscarTrabajador(String nombreTrabajador) {
		return oR.buscarTrabajador(nombreTrabajador);
	}

	@Override
	public List<Orden_compra> buscarProveedor(String nombreProveedor) {
		return oR.buscarProveedor(nombreProveedor);
	}

	@Override
	public List<Orden_compra> buscar(String notaOrden) {
		return oR.findByNotaOrden(notaOrden);
	}
}
