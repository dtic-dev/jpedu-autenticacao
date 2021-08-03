package com.br.pmjp.dtic.jpedu.autenticacao.security;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.br.pmjp.dtic.jpedu.autenticacao.model.Aluno;
import com.br.pmjp.dtic.jpedu.autenticacao.model.Professor;
import com.br.pmjp.dtic.jpedu.autenticacao.model.UsuarioAcesso;
import com.br.pmjp.dtic.jpedu.autenticacao.oath2.CustomOAuth2User;
import com.br.pmjp.dtic.jpedu.autenticacao.oath2.CustomOAuth2UserService;
import com.br.pmjp.dtic.jpedu.autenticacao.service.AlunoService;
import com.br.pmjp.dtic.jpedu.autenticacao.service.ProfessorService;
import com.br.pmjp.dtic.jpedu.autenticacao.service.UsuarioAcessoService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomOAuth2UserService oauthUserService;
	
	@Autowired
	private UsuarioAcessoService usuarioAcessoService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private ProfessorService professorService;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/login", "/oauth/**").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("pass")
				.defaultSuccessUrl("/list")
			.and()
			.oauth2Login()
				.loginPage("/login")
				.userInfoEndpoint()
					.userService(oauthUserService)
				.and()
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						System.out.println("AuthenticationSuccessHandler invoked");
						System.out.println("Authentication name: " + authentication.getName());
						CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
						
						usuarioAcessoService.processOAuthPostLogin(oauthUser.getEmail(), authentication.getName());
						
						Aluno aluno = alunoService.getAluno(oauthUser.getEmail());
						Professor professor = professorService.getProfessor(oauthUser.getEmail());
						UsuarioAcesso usuarioAcesso = usuarioAcessoService.getUsuarioAcesso(oauthUser.getEmail());
//						if(aluno == null || professor == null) {
//							response.sendRedirect("/erro");
//						}
						RequestDispatcher dispatcher = request.getRequestDispatcher("/sucesso");
						request.setAttribute("nome", authentication.getName());
						// TODO encontrar um forma de enviar a imagem
						dispatcher.forward(request, response);
					}
				})
				//.defaultSuccessUrl("/list")
			.and()
			.logout().logoutSuccessUrl("/").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
}
