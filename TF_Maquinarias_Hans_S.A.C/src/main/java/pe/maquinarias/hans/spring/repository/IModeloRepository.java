package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Modelo;


@Repository
public interface IModeloRepository extends JpaRepository<Modelo,Integer>{
	
	@Query("select count(mo.nameModelo )from Modelo mo where mo.nameModelo =:nameModelo ")
	public int searchNameModelo(@Param("nameModelo") String nameModelo);
	
	@Query("from Modelo mo where mo.nameModelo like %:nameModelo%")
	List<Modelo> buscarNombre(@Param("nameModelo") String nameModelo);
}