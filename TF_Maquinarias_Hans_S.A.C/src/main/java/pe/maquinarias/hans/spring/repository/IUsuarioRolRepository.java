package pe.maquinarias.hans.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.UsuarioRol;

@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, Integer>{
	public UsuarioRol findByUsername(String username);
}
