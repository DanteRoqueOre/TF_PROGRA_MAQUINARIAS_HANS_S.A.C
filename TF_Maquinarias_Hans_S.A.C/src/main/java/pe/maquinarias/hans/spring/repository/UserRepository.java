package pe.maquinarias.hans.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.maquinarias.hans.spring.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	public Users findByUsername(String username);
}