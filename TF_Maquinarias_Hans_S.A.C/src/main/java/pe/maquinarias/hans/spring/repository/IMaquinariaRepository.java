package pe.maquinarias.hans.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Maquinaria;

@Repository
public interface IMaquinariaRepository  extends JpaRepository<Maquinaria,Integer> {
	@Query("from Maquinaria mq where mq.nameMaquinaria like %:nameMaquinaria%")
	List<Maquinaria> buscarNombre(@Param("nameMaquinaria") String nameMaquinaria);
	
	@Query("from Maquinaria mq where mq.modelo.nameModelo like %:nameModelo%")
	List<Maquinaria> buscarModelo(@Param("nameModelo") String nameModelo);	
	
	@Query("from Maquinaria mq where mq.marca.nameMarca like %:nameMarca%")
	List<Maquinaria> buscarMarca (@Param("nameMarca") String nameMarca);	

	@Query("from Maquinaria mq where mq.tipo.nameTipo like %:nameTipo%")
	List<Maquinaria> buscarTipo (@Param("nameTipo") String nameTipo);	
	
	List<Maquinaria> findBydateMaquinaria(Date dateMaquinaria);
}
