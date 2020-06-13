package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import pe.maquinarias.hans.spring.model.Maquinaria;
import pe.maquinarias.hans.spring.repository.IMaquinariaRepository;
import pe.maquinarias.hans.spring.service.IMaquinariaService;

@Service
public class MaquinariaServiceImpl implements IMaquinariaService{
	
	@Autowired
	private IMaquinariaRepository mqMaquinaria;
	
	@Override
	@Transactional
	public boolean insertar (Maquinaria maquinaria) {
		Maquinaria objMaquinaria= mqMaquinaria.save(maquinaria);
		if(objMaquinaria==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Maquinaria maquinaria) {
		boolean flag = false;
		try {
			mqMaquinaria.save(maquinaria);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche....");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idMaquinaria) {
		mqMaquinaria.deleteById(idMaquinaria);		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Maquinaria> buscarId(int idMaquinaria) {
		return mqMaquinaria.findById(idMaquinaria);
	}	

	@Override
	@Transactional(readOnly=true)
	public Optional<Maquinaria> listarId(int idMaquinaria) {
		return mqMaquinaria.findById(idMaquinaria);
	}
	
	@Override
	@Transactional
	public List<Maquinaria> listar() {
		return mqMaquinaria.findAll();
	}

	@Override
	@Transactional
	public List<Maquinaria> buscarNombre(String nameMaquinaria) {
		return mqMaquinaria.buscarNombre(nameMaquinaria);
	}
	@Override
	@Transactional
	public List<Maquinaria> buscarModelo(String nameModelo) {
		return mqMaquinaria.buscarModelo(nameModelo);
	}

	@Override
	@Transactional
	public List<Maquinaria> buscarMarca(String nameMarca) {
		return mqMaquinaria.buscarMarca(nameMarca);
	}
	
	@Override
	@Transactional
	public List<Maquinaria> buscarTipo(String nameTipo) {
		return mqMaquinaria.buscarTipo(nameTipo);
	}
}