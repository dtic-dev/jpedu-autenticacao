package com.br.pmjp.dtic.jpedu.autenticacao.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticacaoController {

	@GetMapping("/")
	public String home() {
		return "HOME";
	}
	
	@GetMapping("/restrita")
	public String restrita() {
		return "Login efetuado";
	}
}
