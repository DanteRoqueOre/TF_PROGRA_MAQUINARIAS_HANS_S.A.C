package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.maquinarias.hans.spring.model.Maquinista;
import pe.maquinarias.hans.spring.repository.IMaquinistaRepository;
import pe.maquinarias.hans.spring.service.IMaquinistaService;

@Service
public class MaquinistaServiceImpl implements IMaquinistaService{
	
	@Autowired
	private IMaquinistaRepository mquiMaquinista;
	
	@Override
	@Transactional
	public boolean insertar (Maquinista maquinista) {
		Maquinista objMaquinista= mquiMaquinista.save(maquinista);
		if(objMaquinista==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Maquinista maquinista) {
		boolean flag = false;
		try {
			mquiMaquinista.save(maquinista);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idMaquinista) {
		mquiMaquinista.deleteById(idMaquinista);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Maquinista> buscarId(int idMaquinista) {
		return mquiMaquinista.findById(idMaquinista);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Maquinista> listarId(int idMaquinista) {
		return mquiMaquinista.findById(idMaquinista);
	}
	
	@Override
	@Transactional
	public List<Maquinista> listar() {
		return mquiMaquinista.findAll();
	}

	@Override
	@Transactional
	public List<Maquinista> buscarNombre(String nameMaquinista) {
		return mquiMaquinista.buscarNombre(nameMaquinista);
	}
	@Override
	@Transactional
	public List<Maquinista> buscarEspecialidad(String nameEspecialidad) {
		return mquiMaquinista.buscarEspecialidad(nameEspecialidad);
	}
}