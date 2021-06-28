package com.br.pmjp.dtic.jpedu.autenticacao.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.br.pmjp.dtic.jpedu.autenticacao.model.MyUserDetails;
import com.br.pmjp.dtic.jpedu.autenticacao.model.UsuarioAcesso;
import com.br.pmjp.dtic.jpedu.autenticacao.repository.UsuarioAcessoRepository;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioAcessoRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		UsuarioAcesso usuario = usuarioRepository.getUsuarioAcessoByEmail(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Could not find user");
		}
		
		return new MyUserDetails(usuario);
	}

}
