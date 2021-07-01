package com.br.pmjp.dtic.jpedu.autenticacao.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class EmailValidation {

	@Getter
	final String emailRegex = "^[a-z0-9.]+@[a-z]+.(\\W|^)joaopessoa.pb.gov.br(\\W|$)?$";
	
	final String emailRegexProfessor = "^[a-z0-9.]+@professor.joaopessoa.pb.gov.br(\\W|$)?$";

	final String emailRegexAluno = "^[a-z0-9.]+@aluno.joaopessoa.pb.gov.br(\\W|$)?$";
	
	public boolean verificarRegex(final String email) {
		Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    boolean matches = false;
    	matches = matcher.find();
		return matches;
	}
	
	public boolean verificarEmailAluno(final String email) {
		Pattern pattern = Pattern.compile(emailRegexAluno);
	    Matcher matcher = pattern.matcher(email);
	    boolean matches = false;
    	matches = matcher.find();
		return matches;
	}
	
	public boolean verificarEmailProfessor(final String email) {
		Pattern pattern = Pattern.compile(emailRegexProfessor);
	    Matcher matcher = pattern.matcher(email);
	    boolean matches = false;
    	matches = matcher.find();
		return matches;
	}
}
