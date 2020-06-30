package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Alquiler;

public interface IAlquilerService {
	public Integer insertar(Alquiler alquiler);
	public boolean modificar(Alquiler alquiler);
	public void eliminar(int idAlquiler);
	public Optional<Alquiler> buscarId(int idAlquiler);
	public Optional<Alquiler> listarId(int idAlquiler);
	List<Alquiler> listar();
	List<Alquiler> buscarNombre(String nameAlquiler);
	List<Alquiler> buscarMaquinaria(String nameMaquinaria);
	List<Alquiler> buscarMaquinista(String nameMaquinista);
	List<Alquiler> buscarUsuario(String nameUsuario);
}