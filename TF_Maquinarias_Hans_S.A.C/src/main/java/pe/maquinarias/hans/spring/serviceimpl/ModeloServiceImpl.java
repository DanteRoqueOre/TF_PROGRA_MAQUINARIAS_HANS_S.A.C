package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.maquinarias.hans.spring.model.Modelo;
import pe.maquinarias.hans.spring.repository.IModeloRepository;
import pe.maquinarias.hans.spring.service.IModeloService;

public class ModeloServiceImpl implements IModeloService{
	
	@Autowired
	private IModeloRepository moModelo;
	
	@Override
	@Transactional
	public boolean insertar(Modelo modelo) {
		Modelo objModelo= moModelo.save(modelo);
		if(objModelo==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Modelo modelo) {
		boolean flag = false;
		try {
			moModelo.save(modelo);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("sucdedio un rochesazo..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idModelo) {
		moModelo.deleteById(idModelo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Modelo> listarId(int idModelo){
		return moModelo.findById(idModelo);
	}
	
	@Override
	@Transactional
	public List<Modelo>listar(){
		return moModelo.findAll();
	}
	
	@Override
	@Transactional
	public List<Modelo>buscarNombre(String nameModelo){
		return moModelo.buscarNombre(nameModelo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Modelo>buscarId(int idModelo){
		return moModelo.findById(idModelo);
	}
}