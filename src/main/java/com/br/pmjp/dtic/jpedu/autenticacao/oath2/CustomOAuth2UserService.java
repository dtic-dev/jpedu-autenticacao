package com.br.pmjp.dtic.jpedu.autenticacao.oath2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.br.pmjp.dtic.jpedu.autenticacao.validation.EmailValidation;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {

	@Autowired
	EmailValidation emailValidation;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user =  super.loadUser(userRequest);
		System.out.println("CustomOAuth2UserService invoked");
		CustomOAuth2User auth2User = new CustomOAuth2User(user);
		boolean validation = emailValidation.verificarRegex(auth2User.getEmail());
		
		return validation ? new CustomOAuth2User(user) : null;
	}

}
