package com.br.pmjp.dtic.jpedu.autenticacao.control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.br.pmjp.dtic.jpedu.autenticacao.dto.UsuarioDTO;

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
	public UsuarioDTO sucesso(@RequestAttribute String nome, @RequestAttribute String email, @RequestAttribute String imagem) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setEmail(email);
		usuarioDTO.setImagem(imagem);
		usuarioDTO.setNome(nome);
		return usuarioDTO;
	}
}
