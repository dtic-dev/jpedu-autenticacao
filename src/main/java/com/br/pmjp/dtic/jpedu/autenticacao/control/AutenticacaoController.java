package com.br.pmjp.dtic.jpedu.autenticacao.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView projectBase() {
	    return new ModelAndView("redirect:/oauth2/authorization/google");
	}
	
	@GetMapping("/sucesso")
	public String sucesso() {
		return "Sucesso";
	}
}
