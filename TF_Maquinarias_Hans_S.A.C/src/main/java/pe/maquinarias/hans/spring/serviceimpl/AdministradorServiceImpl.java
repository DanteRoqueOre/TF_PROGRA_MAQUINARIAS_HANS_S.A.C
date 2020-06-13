package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.maquinarias.hans.spring.model.Administrador;
import pe.maquinarias.hans.spring.repository.IAdministradorRepository;
import pe.maquinarias.hans.spring.service.IAdministradorService;

@Service
public class AdministradorServiceImpl implements IAdministradorService{
	
	@Autowired
	private IAdministradorRepository adAdministrador;
	
	@Override
	@Transactional
	public boolean insertar (Administrador administrador) {
		Administrador objAdministrador = adAdministrador .save(administrador );
		if(objAdministrador==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Administrador administrador ) {
		boolean flag = false;
		try {
			adAdministrador.save(administrador);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idAdministrador) {
		adAdministrador.deleteById(idAdministrador);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Administrador> buscarId(int idAdministrador) {
		return adAdministrador.findById(idAdministrador);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Administrador> listarId(int idAdministrador) {
		return adAdministrador.findById(idAdministrador);
	}
	
	@Override
	@Transactional
	public List<Administrador> listar() {
		return adAdministrador.findAll();
	}

	@Override
	@Transactional
	public List<Administrador> buscarNombre(String nameAdministrador) {
		return adAdministrador.buscarNombre(nameAdministrador);
	}
	@Override
	@Transactional
	public List<Administrador> buscarCargo(String nameCargo) {
		return adAdministrador.buscarCargo(nameCargo);
	}
}