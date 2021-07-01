package com.br.pmjp.dtic.jpedu.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

	Professor findByEmail(String email);
	
}
