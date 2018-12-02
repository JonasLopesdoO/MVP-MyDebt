package com.ufc.br.mvp.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.repository.UsuarioRepository;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository ur;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario user = ur.findByEmail(login);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}
		return new User(user.getUsername(), user.getPassword(), true, true, true, true, user.getAuthorities());
	}

}
