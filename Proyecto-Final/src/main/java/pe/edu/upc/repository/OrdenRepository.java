package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Orden_compra;


@Repository
public interface OrdenRepository extends JpaRepository<Orden_compra, Integer>{

	@Query("select o from Orden_compra o where o.trabajadororden.nombreTrabajador like %?1%")
	public List<Orden_compra> buscarTrabajador(String nombreTrabajador);

	@Query("select o from Orden_compra o where o.proveedororden.nombreProveedor like %?1%")
	public List<Orden_compra> buscarProveedor(String nombreProveedor);
	
	@Query("select o from Orden_compra o where o.notaOrden like %?1%")
	public List<Orden_compra> findByNotaOrden(String term);
}
