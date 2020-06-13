package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Administrador;

public interface IAdministradorService {
	public boolean insertar(Administrador administrador);
	public boolean modificar(Administrador administrador);
	public void eliminar(int idAdministrador);
	public Optional<Administrador> buscarId(int idAdministrador);
	public Optional<Administrador> listarId(int idAdministrador);
	List<Administrador> listar();
	List<Administrador> buscarNombre(String nameAdministrador);
	List<Administrador> buscarCargo(String nameCargo);
}