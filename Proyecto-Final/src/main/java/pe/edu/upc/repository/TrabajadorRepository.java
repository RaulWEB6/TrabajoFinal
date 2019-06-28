package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Trabajador;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {

	@Query("from Trabajador t where t.nombreTrabajador like %:nombreTrabajador%")
	List<Trabajador> findByNombreTrabajador(String nombreTrabajador);

	@Query("select count(t.dniTrabajador) from Trabajador t where t.dniTrabajador =:dniTrabajador")
	public int buscarDniTrabajador(@Param("dniTrabajador") String dniTrabajador);

}
