package com.br.pmjp.dtic.jpedu.autenticacao.control;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.pmjp.dtic.jpedu.autenticacao.control.AutenticacaoController;

@SpringBootTest
public class SmokeTest {

	@Autowired
	private AutenticacaoController controller;
	
	@Test
	public void contextLoads() throws Exception {
		Assertions.assertThat(controller).isNotNull();
	}
}
