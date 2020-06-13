package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Modelo;

public interface IModeloService {
	public boolean insertar(Modelo modelo);
	public boolean modificar(Modelo modelo);
	public void eliminar(int idModelo);
	public Optional<Modelo> buscarId(int idModelo);
	public Optional<Modelo> listarId(int idModelo);
	List<Modelo> listar();
	List<Modelo> buscarNombre(String nameModelo);
}