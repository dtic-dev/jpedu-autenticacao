package com.br.pmjp.dtic.jpedu.autenticacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.br.pmjp.dtic.jpedu.autenticacao.model.UsuarioAcesso;

public interface UsuarioAcessoRepository extends JpaRepository<UsuarioAcesso, Integer> {
	@Query("SELECT ua FROM UsuarioAcesso ua WHERE ua.email = :email")
	public UsuarioAcesso getUsuarioAcessoByEmail(@Param("email") String email);
}
