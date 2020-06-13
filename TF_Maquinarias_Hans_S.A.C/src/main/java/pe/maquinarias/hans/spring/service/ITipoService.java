package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Tipo;

public interface ITipoService {
	public boolean insertar(Tipo tipo);
	public boolean modificar(Tipo tipo);
	public void eliminar(int idTipo);
	public Optional<Tipo> buscarId(int idTipo);
	public Optional<Tipo> listarId(int idTipo);
	List<Tipo> listar();
	List<Tipo> buscarNombre(String nameTipo);
}