package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Cargo;

public interface ICargoService {
	public Integer insertar(Cargo cargo);
	public boolean modificar(Cargo cargo);
	public void eliminar(int idCargo);
	public Optional<Cargo> buscarId(int idCargo);
	public Optional<Cargo> listarId(int idCargo);
	List<Cargo> listar();
	List<Cargo> buscarNombre(String nameCargo);
}