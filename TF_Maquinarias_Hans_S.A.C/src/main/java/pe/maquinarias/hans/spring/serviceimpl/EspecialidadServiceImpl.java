package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pe.maquinarias.hans.spring.model.Especialidad;
import pe.maquinarias.hans.spring.repository.IEspecialidadRepository;
import pe.maquinarias.hans.spring.service.IEspecialidadService;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{
	
	@Autowired
	private IEspecialidadRepository eEspecialidad;
	
	@Override
	@Transactional
	public boolean insertar(Especialidad especialidad) {
		Especialidad objEspecialidad= eEspecialidad.save(especialidad);
		if(objEspecialidad==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Especialidad especialidad) {
		boolean flag = false;
		try {
			eEspecialidad.save(especialidad);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("sucdedio un rochesazo..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idEspecialidad) {
		eEspecialidad.deleteById(idEspecialidad);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Especialidad> listarId(int idEspecialidad){
		return eEspecialidad.findById(idEspecialidad);
	}
	
	@Override
	@Transactional
	public List<Especialidad>listar(){
		return eEspecialidad.findAll();
	}
	
	@Override
	@Transactional
	public List<Especialidad>buscarNombre(String nameEspecialidad){
		return eEspecialidad.buscarNombre(nameEspecialidad);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Especialidad>buscarId(int idEspecialidad){
		return eEspecialidad.findById(idEspecialidad);
	}
}