package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.maquinarias.hans.spring.model.Alquiler;
import pe.maquinarias.hans.spring.repository.IAlquilerRepository;
import pe.maquinarias.hans.spring.service.IAlquilerService;

@Service
public class AlquilerServiceImpl implements IAlquilerService{
	
	@Autowired
	private IAlquilerRepository aqAlquiler;
	
	@Override
	@Transactional
	public boolean insertar (Alquiler alquiler) {
		Alquiler objAlquiler= aqAlquiler.save(alquiler);
		if(objAlquiler==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Alquiler alquiler) {
		boolean flag = false;
		try {
			aqAlquiler.save(alquiler);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idAlquiler) {
		aqAlquiler.deleteById(idAlquiler);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Alquiler> buscarId(int idAlquiler) {
		return aqAlquiler.findById(idAlquiler);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Alquiler> listarId(int idAlquiler) {
		return aqAlquiler.findById(idAlquiler);
	}
	
	@Override
	@Transactional
	public List<Alquiler> listar() {
		return aqAlquiler.findAll();
	}

	@Override
	@Transactional
	public List<Alquiler> buscarNombre(String nameAlquiler) {
		return aqAlquiler.buscarNombre(nameAlquiler);
	}
	@Override
	@Transactional
	public List<Alquiler> buscarMaquinaria(String nameMaquinaria) {
		return aqAlquiler.buscarMaquinaria(nameMaquinaria);
	}

	@Override
	@Transactional
	public List<Alquiler> buscarMaquinista(String nameMaquinista) {
		return aqAlquiler.buscarMaquinista(nameMaquinista);
	}
	
	@Override
	@Transactional
	public List<Alquiler> buscarUsuario(String nameUsuario) {
		return aqAlquiler.buscarUsuario(nameUsuario);
	}
}