package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pe.maquinarias.hans.spring.model.Usuario;
import pe.maquinarias.hans.spring.repository.IUsuarioRepository;
import pe.maquinarias.hans.spring.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	
	@Autowired
	private IUsuarioRepository uUsuario;
	
	@Override
	@Transactional
	public Integer insertar(Usuario usuario) {
		int rpta = uUsuario.searchDniUsuario(usuario.getDniUsuario());
		
		if (rpta == 0) {
			uUsuario.save(usuario);
		}
		return rpta;
	
	}
	
	@Override
	@Transactional
	public boolean modificar(Usuario usuario) {
		boolean flag = false;
		try {
			uUsuario.save(usuario);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("sucdedio un rochesazo..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idUsuario) {
		uUsuario.deleteById(idUsuario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Usuario> listarId(int idUsuario){
		return uUsuario.findById(idUsuario);
	}
	
	@Override
	@Transactional
	public List<Usuario>listar(){
		return uUsuario.findAll();
	}
	
	@Override
	@Transactional
	public List<Usuario>buscarNombre(String nameUsuario){
		return uUsuario.buscarNombre(nameUsuario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Usuario>buscarId(int idUsuario){
		return uUsuario.findById(idUsuario);
	}
}