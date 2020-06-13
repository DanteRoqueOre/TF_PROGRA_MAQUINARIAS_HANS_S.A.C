package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.maquinarias.hans.spring.model.Marca;
import pe.maquinarias.hans.spring.repository.IMarcaRepository;
import pe.maquinarias.hans.spring.service.IMarcaService;

public class MarcaServiceImpl implements IMarcaService{
	
	@Autowired
	private IMarcaRepository maMarca;
	
	@Override
	@Transactional
	public boolean insertar(Marca marca) {
		Marca objMarca= maMarca.save(marca);
		if(objMarca==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Marca marca) {
		boolean flag = false;
		try {
			maMarca.save(marca);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("sucdedio un rochesazo..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idMarca) {
		maMarca.deleteById(idMarca);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Marca> listarId(int idMarca){
		return maMarca.findById(idMarca);
	}
	
	@Override
	@Transactional
	public List<Marca>listar(){
		return maMarca.findAll();
	}
	
	@Override
	@Transactional
	public List<Marca>buscarNombre(String nameMarca){
		return maMarca.buscarNombre(nameMarca);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Marca>buscarId(int idMarca){
		return maMarca.findById(idMarca);
	}
}