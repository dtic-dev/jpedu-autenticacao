package com.br.pmjp.dtic.jpedu.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}