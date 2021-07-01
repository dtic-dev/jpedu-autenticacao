package com.br.pmjp.dtic.jpedu.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Professor;
import com.br.pmjp.dtic.jpedu.autenticacao.repository.ProfessorRepository;

@Service
public class ProfessorService extends UsuarioAcessoService {

	@Autowired
	private ProfessorRepository repo;
	
	public Professor getProfessor(String email) {
		return repo.findByEmail(email);
	}

}
