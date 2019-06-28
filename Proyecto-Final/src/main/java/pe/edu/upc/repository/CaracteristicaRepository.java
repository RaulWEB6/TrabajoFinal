package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Caracteristica;

@Repository
public interface CaracteristicaRepository extends JpaRepository<Caracteristica, Integer> {

	@Query("select c from Caracteristica c where c.nombreCaracteristica like %?1%")
	public List<Caracteristica> findByNombreCaracteristica(String term);

	@Query("select c from Caracteristica c where c.recursocaracteristica.nombreRecurso like %?1%")
	public List<Caracteristica> buscarRecurso(String nombreRecurso);

	public List<Caracteristica> findByNombreCaracteristicaLikeIgnoreCase(String term);

}
