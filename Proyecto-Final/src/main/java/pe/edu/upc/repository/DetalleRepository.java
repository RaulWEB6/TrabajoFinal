package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.edu.upc.entity.Detalle_orden;

public interface DetalleRepository extends JpaRepository<Detalle_orden, Integer>{
	
	@Query("select d from Detalle_orden d where d.ordendetalle.idOrden like %?1%")
	public List<Detalle_orden> buscarOrden(int idOrden);

	@Query("select d from Detalle_orden d where d.recursodetalle.nombreRecurso like %?1%")
	public List<Detalle_orden> buscarRecurso(String nombreProveedor);
	
	@Query("select d from Detalle_orden d where d.idDetalle like %?1%")
	public List<Detalle_orden> findByIdDetalle(int term);
}
