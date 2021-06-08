package com.br.pmjp.dtic.jpedu.autenticacao.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Email
	private String email;
	
//	jonny.wilson@educa.joaopessoa.pb.gov.br
	
//	^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.([a-z]+)?$
			
			
	// /^[a-z0-9.]+@[a-z]+.(\W|^)joaopessoa.pb.gov.br(\W|$)?$/i
	 
}
