package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import pe.maquinarias.hans.spring.model.Tipo;
import pe.maquinarias.hans.spring.repository.ITipoRepository;
import pe.maquinarias.hans.spring.service.ITipoService;

public class TipoServiceImpl implements ITipoService{
	
	@Autowired
	private ITipoRepository tTipo;
	
	@Override
	@Transactional
	public boolean insertar(Tipo tipo) {
		Tipo objTipo= tTipo.save(tipo);
		if(objTipo==null)
			return false;
		else
			return true;
	}
	
	@Override
	@Transactional
	public boolean modificar(Tipo tipo) {
		boolean flag = false;
		try {
			tTipo.save(tipo);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("sucdedio un rochesazo..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idTipo) {
		tTipo.deleteById(idTipo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Tipo> listarId(int idTipo){
		return tTipo.findById(idTipo);
	}
	
	@Override
	@Transactional
	public List<Tipo>listar(){
		return tTipo.findAll();
	}
	
	@Override
	@Transactional
	public List<Tipo>buscarNombre(String nameTipo){
		return tTipo.buscarNombre(nameTipo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Tipo>buscarId(int idTipo){
		return tTipo.findById(idTipo);
	}
}