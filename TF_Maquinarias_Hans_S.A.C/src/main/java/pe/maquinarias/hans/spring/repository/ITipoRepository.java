package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Tipo;

@Repository
public interface ITipoRepository extends JpaRepository<Tipo,Integer>{
	
	@Query("select count(t.nameTipo)from Tipo t where t.nameTipo=nameTipo")
	public int searchNameTipo(@Param("nameTipo") String nameTipo);
	
	@Query("from Tipo t where t.nameTipo like %:nameTipo%")
	List<Tipo> buscarNombre(@Param("nameTipo") String nameTipo);
}