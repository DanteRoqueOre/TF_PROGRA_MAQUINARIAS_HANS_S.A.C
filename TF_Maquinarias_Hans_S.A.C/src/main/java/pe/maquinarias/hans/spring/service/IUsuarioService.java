package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Usuario;

public interface IUsuarioService {
	public boolean insertar(Usuario usuario);
	public boolean modificar(Usuario usuario);
	public void eliminar(int idUsuario);
	public Optional<Usuario> buscarId(int idUsuario);
	public Optional<Usuario> listarId(int idUsuario);
	List<Usuario> listar();
	List<Usuario> buscarNombre(String nameUsuario);
}