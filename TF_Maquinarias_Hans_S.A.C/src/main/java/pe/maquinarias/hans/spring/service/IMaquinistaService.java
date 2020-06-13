package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Maquinista;

public interface IMaquinistaService {
	public boolean insertar(Maquinista maquinista);
	public boolean modificar(Maquinista maquinista);
	public void eliminar(int idMaquinista);
	public Optional<Maquinista> buscarId(int idMaquinista);
	public Optional<Maquinista> listarId(int idMaquinista);
	List<Maquinista> listar();
	List<Maquinista> buscarNombre(String nameMaquinista);
	List<Maquinista> buscarEspecialidad(String nameEspecialidad);
}