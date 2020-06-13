package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Administrador;

@Repository
public interface IAdministradorRepository  extends JpaRepository<Administrador,Integer> {
	@Query("from Administrador ad where ad.nameAdministrador like %:nameAdministrador%")
	List<Administrador> buscarNombre(@Param("nameAdministrador") String nameAdministrador);
	
	@Query("from Administrador ad where ad.cargo.nameCargo like %:nameCargo%")
	List<Administrador> buscarCargo(@Param("nameCargo") String nameCargo);	
}