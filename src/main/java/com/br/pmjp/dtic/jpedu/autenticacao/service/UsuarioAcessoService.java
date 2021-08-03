package com.br.pmjp.dtic.jpedu.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Professor;
import com.br.pmjp.dtic.jpedu.autenticacao.model.UsuarioAcesso;
import com.br.pmjp.dtic.jpedu.autenticacao.model.enumeration.Provedor;
import com.br.pmjp.dtic.jpedu.autenticacao.repository.ProfessorRepository;
import com.br.pmjp.dtic.jpedu.autenticacao.repository.UsuarioAcessoRepository;

@Service
public class UsuarioAcessoService {

	@Autowired
	private UsuarioAcessoRepository repo;
	
	public void processOAuthPostLogin(String login, String nome) {
		UsuarioAcesso existUser = repo.getUsuarioAcessoByEmail(login);
		
		if (existUser == null) {
			UsuarioAcesso newUser = new UsuarioAcesso();
			newUser.setEmail(login);
			newUser.setProvedor(Provedor.GOOGLE);
			newUser.setNome(nome);
			newUser.setAtivo(true);			
			
			repo.save(newUser);
			
			System.out.println("Created new user: " + login);
		}
	}
	
	public UsuarioAcesso getUsuarioAcesso(String email) {
		return repo.getUsuarioAcessoByEmail(email);
	}
	
}
