package pe.maquinarias.hans.spring.service;

import java.util.List;
import java.util.Optional;

import pe.maquinarias.hans.spring.model.Maquinaria;

public interface IMaquinariaService {
	public boolean insertar(Maquinaria maquinaria);
	public boolean modificar(Maquinaria maquinaria);
	public void eliminar(int idMaquinaria);
	public Optional<Maquinaria> buscarId(int idMaquinaria);
	public Optional<Maquinaria> listarId(int idMaquinaria);
	List<Maquinaria> listar();
	List<Maquinaria> buscarNombre(String nameMaquinaria);
	List<Maquinaria> buscarModelo(String nameModelo);
	List<Maquinaria> buscarMarca(String nameMarca);
	List<Maquinaria> buscarTipo(String nameTipo);
}