package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Marca;

public interface IMarcaService {
	public boolean insertar(Marca marca);
	public boolean modificar(Marca marca);
	public void eliminar(int idMarca);
	public Optional<Marca> buscarId(int idMarca);
	public Optional<Marca> listarId(int idMarca);
	List<Marca> listar();
	List<Marca> buscarNombre(String nameMarca);
}