package com.br.pmjp.dtic.jpedu.autenticacao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
public class Aluno extends UsuarioAcesso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	 
	@Column(name = "id_unico")
	private Integer idUnico;
	
	@Column(length = 100, name = "primeiro_nome")
	private String primeiroNome; 

	@Column(length = 100, name = "ultimo_nome")
	private String ultimoNome;
	
	@Column(length = 150, name = "nome_mae")
	private String nomeMae;
	
	@Column(length = 150, name = "nome_pai")
	private String nomePai;
	
	@Column(length = 1, name = "sexo")
	private char sexo;
	
	@Column(length = 11, name = "nis")
	private String nis;
	
	@Column(length = 20, name = "identidade_numero")
	private String identidadeNumero; 
	
	@Column(length = 50, name = "identidade_orgao")
	private String identidadeOrgao;
	
	@Column(length = 20, name = "identidade_experdicao")
	@Temporal(TemporalType.DATE)
	private Date identidadeExpedicao;
	
	@Column(length = 2, name = "identidade_uf")
	private String identidadeUf;
	
	@Column(length = 150, name = "endereco_logradouro")
	private String enderecoLogradouro;
	
	@Column(length = 20, name = "endereco_numero")
	private String enderecoNumero;
	
	@Column(length = 20, name = "endereco_complemento")
	private String enderecoComplemento;
	
	@Column(length = 100, name = "endereco_bairro")
	private String enderecoBairro;
	
	@Column(length = 8, name = "endereco_cep")
	private String enderecoCep;
	
	@Column(name = "cor_raca")
	private Integer corRaca;
	
	@Column(name = "filiacao")
	private Integer filiacao;
}