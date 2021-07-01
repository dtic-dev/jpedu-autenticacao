package com.br.pmjp.dtic.jpedu.autenticacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Aluno;
import com.br.pmjp.dtic.jpedu.autenticacao.repository.AlunoRepository;

@Service
public class AlunoService extends UsuarioAcessoService {

	@Autowired
	private AlunoRepository repo;
	
	public Aluno getAluno(String email) {
		return repo.findByEmail(email);
	}

}
