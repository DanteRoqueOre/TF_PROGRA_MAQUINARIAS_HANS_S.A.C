package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Marca;


@Repository
public interface IMarcaRepository extends JpaRepository<Marca,Integer>{
	@Query("from Marca ma where ma.nameMarca like %:nameMarca%")
	List<Marca> buscarNombre(@Param("nameMarca") String nameMarca);
}