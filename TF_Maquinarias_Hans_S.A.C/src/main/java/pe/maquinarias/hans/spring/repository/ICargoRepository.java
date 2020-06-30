package pe.maquinarias.hans.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Cargo;

@Repository
public interface ICargoRepository extends JpaRepository<Cargo,Integer>{
	
	@Query("select count(ca.nameCargo)from Cargo ca where ca.nameCargo=:nameCargo")
	public int searchNameCargo(@Param("nameCargo") String nameCargo);
	
	@Query("from Cargo ca where ca.nameCargo like %:nameCargo%")
	List<Cargo> buscarNombre(@Param("nameCargo") String nameCargo);
}