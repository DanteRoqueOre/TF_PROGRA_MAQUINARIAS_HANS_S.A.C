package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Especialidad;

public interface IEspecialidadService {
	public boolean insertar(Especialidad especialidad);
	public boolean modificar(Especialidad especialidad);
	public void eliminar(int idEspecialidad);
	public Optional<Especialidad> buscarId(int idEspecialidad);
	public Optional<Especialidad> listarId(int idEspecialidad);
	List<Especialidad> listar();
	List<Especialidad> buscarNombre(String nameEspecialidad);
}