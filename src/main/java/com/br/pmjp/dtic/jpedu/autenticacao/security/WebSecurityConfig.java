package com.br.pmjp.dtic.jpedu.autenticacao.security;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                                   "/configuration/ui",
                                   "/swagger-resources/**",
                                   "/configuration/security",
                                   "/swagger-ui.html",
                                   "/webjars/**");
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
				.and().failureHandler(null)
				.successHandler((HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) -> {
						System.out.println("AuthenticationSuccessHandler invoked");
						System.out.println("Authentication nome: " + authentication.getName());
						CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
						Map<String, Object> attributes = oauthUser.getAttributes();
						
						usuarioAcessoService.processOAuthPostLogin(oauthUser.getEmail(), authentication.getName());
						
						RequestDispatcher dispatcher = request.getRequestDispatcher("/sucesso");
						request.setAttribute("nome", authentication.getName());
						request.setAttribute("email", attributes.get("email"));
						request.setAttribute("imagem", attributes.get("picture"));
						// TODO encontrar um forma de enviar a imagem
						dispatcher.forward(request, response);
					})
			.failureHandler((HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authentication) -> {
				System.out.println("AuthenticationFailureHandler invoked");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("/error	");
				// TODO encontrar um forma de enviar a imagem
				dispatcher.forward(request, response);
			})
			.and()
			.logout().logoutSuccessUrl("/").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
}
