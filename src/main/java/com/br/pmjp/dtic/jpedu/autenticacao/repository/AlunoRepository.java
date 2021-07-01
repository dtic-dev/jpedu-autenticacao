package com.br.pmjp.dtic.jpedu.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

	Aluno findByEmail(String email);
}
