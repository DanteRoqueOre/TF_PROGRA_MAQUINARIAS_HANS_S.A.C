package pe.maquinarias.hans.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import pe.maquinarias.hans.spring.model.Cargo;
import pe.maquinarias.hans.spring.repository.ICargoRepository;
import pe.maquinarias.hans.spring.service.ICargoService;

@Service
public class CargoServiceImpl implements ICargoService{
	
	@Autowired
	private ICargoRepository caCargo;
	
	@Override
	@Transactional
	public Integer insertar(Cargo cargo) {
		 int rpta = caCargo.searchNameCargo(cargo.getNameCargo());
			
			if (rpta == 0) {
				caCargo.save(cargo);
			}
			return rpta;
		
		}
	
	@Override
	@Transactional
	public boolean modificar(Cargo cargo) {
		boolean flag = false;
		try {
			caCargo.save(cargo);
			flag=true;
		}
		catch(Exception ex) {
			System.out.println("sucdedio un rochesazo..");
		}
		return flag;
	}
	
	@Override
	@Transactional
	public void eliminar(int idCargo) {
		caCargo.deleteById(idCargo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Cargo> listarId(int idCargo){
		return caCargo.findById(idCargo);
	}
	
	@Override
	@Transactional
	public List<Cargo>listar(){
		return caCargo.findAll();
	}
	
	@Override
	@Transactional
	public List<Cargo>buscarNombre(String nameCargo){
		return caCargo.buscarNombre(nameCargo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Optional<Cargo>buscarId(int idCargo){
		return caCargo.findById(idCargo);
	}
}