package com.br.pmjp.dtic.jpedu.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pmjp.dtic.jpedu.autenticacao.model.UsuarioAcesso;
import com.br.pmjp.dtic.jpedu.autenticacao.model.enumeration.Provedor;
import com.br.pmjp.dtic.jpedu.autenticacao.repository.UsuarioAcessoRepository;

@Service
public class UsuarioAcessoService {

	@Autowired
	private UsuarioAcessoRepository repo;
	
	public void processOAuthPostLogin(String username) {
		UsuarioAcesso existUser = repo.getUsuarioAcessoByEmail(username);
		
		if (existUser == null) {
			UsuarioAcesso newUser = new UsuarioAcesso();
			newUser.setEmail(username);
			newUser.setProvedor(Provedor.GOOGLE);
			newUser.setAtivo(true);			
			
			repo.save(newUser);
			
			System.out.println("Created new user: " + username);
		}
	}
	
}
