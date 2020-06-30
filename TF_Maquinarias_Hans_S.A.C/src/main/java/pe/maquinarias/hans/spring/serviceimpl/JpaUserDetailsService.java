package pe.maquinarias.hans.spring.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.maquinarias.hans.spring.repository.IUsuarioRolRepository;
import pe.maquinarias.hans.spring.model.Role;
import pe.maquinarias.hans.spring.model.UsuarioRol;


@Service
public class JpaUserDetailsService implements UserDetailsService {

	@Autowired
	private IUsuarioRolRepository usuarioRolDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsuarioRol usuarioRol = usuarioRolDao.findByUsername(username);

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (Role role : usuarioRol.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
		}

		return new User(usuarioRol.getUsername(), usuarioRol.getPassword(), usuarioRol.isEnabled(), true, true, true,
				authorities);
	}

}
