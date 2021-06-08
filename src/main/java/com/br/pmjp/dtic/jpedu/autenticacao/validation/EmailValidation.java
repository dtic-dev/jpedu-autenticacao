package com.br.pmjp.dtic.jpedu.autenticacao.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
public class EmailValidation {

	@Getter
	final String emailRegex = "^[a-z0-9.]+@[a-z]+.(\\W|^)joaopessoa.pb.gov.br(\\W|$)?$";

	public boolean verificarRegex(final String email) {
		Pattern pattern = Pattern.compile(emailRegex);
	    Matcher matcher = pattern.matcher(email);
	    boolean matches = false;
    	matches = matcher.find();
		return matches;
	}
}
