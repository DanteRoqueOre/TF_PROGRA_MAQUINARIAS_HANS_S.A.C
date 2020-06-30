package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Especialidad;

@Repository
public interface IEspecialidadRepository extends JpaRepository<Especialidad,Integer>{
	
	@Query("select count(es.nameEspecialidad)from Especialidad es where es.nameEspecialidad=:nameEspecialidad")
	public int searchNameEspecialidad(@Param("nameEspecialidad") String nameEspecialidad);
	
	@Query("from Especialidad es where es.nameEspecialidad like %:nameEspecialidad%")
	List<Especialidad> buscarNombre(@Param("nameEspecialidad") String nameEspecialidad);
}