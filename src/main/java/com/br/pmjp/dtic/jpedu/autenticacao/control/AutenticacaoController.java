package com.br.pmjp.dtic.jpedu.autenticacao.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AutenticacaoController {

	@GetMapping("/")
	public String home() {
		return "HOME";
	}
	
	@RequestMapping(value="error", method = RequestMethod.GET, produces = "application/json" )
	public String restrita() {
		return "Erro";
	}
	
	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public ModelAndView projectBase() {
	    return new ModelAndView("redirect:/oauth2/authorization/google");
	}
	
	@RequestMapping(value="sucesso", method = RequestMethod.GET, produces = "application/json" )
	@ResponseBody
	public String sucesso(@RequestAttribute String nome) {
		return nome;
	}
}
