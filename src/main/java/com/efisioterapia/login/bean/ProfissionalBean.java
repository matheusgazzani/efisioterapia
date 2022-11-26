package com.efisioterapia.login.bean;

import java.util.Date;

public class ProfissionalBean {
	
	private Integer cod_fisioterapeuta;
	private String nome;
	private String telefone;
	private Date dt_nascimento;
	private String email;
	private String sexo;
	private String crefito;
	private String especialidade;
	private String cpf;
	
	public ProfissionalBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProfissionalBean(Integer cod_fisioterapeuta, String nome, String telefone, Date dt_nascimento,
			String email, String sexo, String crefito, String especialidade, String cpf) {
		super();
		this.cod_fisioterapeuta = cod_fisioterapeuta;
		this.nome = nome;
		this.telefone = telefone;
		this.dt_nascimento = dt_nascimento;
		this.email = email;
		this.sexo = sexo;
		this.crefito = crefito;
		this.especialidade = especialidade;
		this.cpf = cpf;
	}
	
	public ProfissionalBean(int id, String nome2) {
		// TODO Auto-generated constructor stub
	}

	public Integer getCod_fisioterapeuta() {
		return cod_fisioterapeuta;
	}
	public void setCod_fisioterapeuta(Integer cod_fisioterapeuta) {
		this.cod_fisioterapeuta = cod_fisioterapeuta;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public Date getDt_nascimento() {
		return dt_nascimento;
	}
	public void setDt_nascimento(Date dt_nascimento) {
		this.dt_nascimento = dt_nascimento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCrefito() {
		return crefito;
	}
	public void setCrefito(String crefito) {
		this.crefito = crefito;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
