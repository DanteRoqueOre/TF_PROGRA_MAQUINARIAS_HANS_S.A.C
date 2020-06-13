package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Maquinista;

@Repository
public interface IMaquinistaRepository  extends JpaRepository<Maquinista,Integer> {
	@Query("from Maquinista mqui where mqui.nameMaquinista like %:nameMaquinista%")
	List<Maquinista> buscarNombre(@Param("nameMaquinista") String nameMaquinista);
	
	@Query("from Maquinista mqui where mqui.especialidad.nameEspecialidad like %:nameEspecialidad%")
	List<Maquinista> buscarEspecialidad(@Param("nameEspecialidad") String nameEspecialidad);	
}