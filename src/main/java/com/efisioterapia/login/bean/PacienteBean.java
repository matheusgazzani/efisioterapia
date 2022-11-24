package com.efisioterapia.login.bean;

import java.util.Date;

// CLASSE QUE REPRESENTA UM ESPELHO DO BANCO DE DADOS
// POSSUI TODOS OS CAMPOS QUE ESTÃO NO BD
public class PacienteBean {
	
	private Integer cod_paciente;
	private String nome;
	private String dt_nascimento;
	private String email;
	private String profissao;
	private String sexo;
	private String cpf;
	private String telefone;
	
	public PacienteBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PacienteBean(Integer cod_paciente, String nome, String dt_nascimento, String email, String profissao,
			String sexo, String cpf, String telefone) {
		super();
		this.cod_paciente = cod_paciente;
		this.nome = nome;
		this.dt_nascimento = dt_nascimento;
		this.email = email;
		this.profissao = profissao;
		this.sexo = sexo;
		this.cpf = cpf;
		this.telefone = telefone;
	}
	
	// GETTERS E SETTERS
	public Integer getCod_paciente() {
		return cod_paciente;
	}
	public void setCod_paciente(Integer cod_paciente) {
		this.cod_paciente = cod_paciente;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(String dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfissao() {
		return profissao;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}	
	
}
