package com.br.pmjp.dtic.jpedu.autenticacao.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RegexTest {
	
	@Autowired
	private EmailValidation emailValidation;
	
	@Test
	public void validateEmailEduca() throws Exception {
		final String email = "jonny.wilson@educa.joaopessoa.pb.gov.br";
		
		boolean matches = emailValidation.verificarRegex(email);
	    assertEquals(matches, true);
	}
	
	@Test
	public void validateEmailProfessor() throws Exception {
		final String email = "joao.bavieira@professor.joaopessoa.pb.gov.br";
		
		boolean matches = emailValidation.verificarEmailProfessor(email);
	    assertEquals(matches, true);
	}
	
	@Test
	public void validateEmailAluno() throws Exception {
		final String email = "kaue.lffreitas@aluno.joaopessoa.pb.gov.br";
		
		boolean matches = emailValidation.verificarEmailAluno(email);
	    assertEquals(matches, true);
	}
	
}
