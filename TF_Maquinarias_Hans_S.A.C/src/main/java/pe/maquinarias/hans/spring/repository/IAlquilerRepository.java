package pe.maquinarias.hans.spring.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Alquiler;

@Repository
public interface IAlquilerRepository  extends JpaRepository<Alquiler,Integer> {
	@Query("from Alquiler aq where aq.nameAlquiler like %:nameAlquiler%")
	List<Alquiler> buscarNombre(@Param("nameAlquiler") String nameAlquiler);
	
	@Query("from Alquiler aq where aq.maquinaria.nameMaquinaria like %:nameMaquinaria%")
	List<Alquiler> buscarMaquinaria(@Param("nameMaquinaria") String nameMaquinaria);	
	
	@Query("from Alquiler aq where aq.maquinista.nameMaquinista like %:nameMaquinista%")
	List<Alquiler> buscarMaquinista (@Param("nameMaquinista") String nameMaquinista);	

	@Query("from Alquiler aq where aq.usuario.nameUsuario like %:nameUsuario%")
	List<Alquiler> buscarUsuario (@Param("nameUsuario") String nameUsuario);	
	
	List<Alquiler> findBydateAlquiler(Date dateAlquiler);
}
