package com.efisioterapia.login.bean;

public class ServicoBean {
	
	private Integer cod_servico;
	private String nome;
	private Float valor;
	private String descricao;
	private Integer cod_fisioterapeuta;
	private String nome_fisioterapeuta;

	public ServicoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/** CONSTRUTORES **/
	public ServicoBean(Integer cod_servico, String nome, Float valor, String descricao, String nome_fisioterapeuta) {
		super();
		this.cod_servico = cod_servico;
		this.nome = nome;
		this.valor = valor;
		this.descricao = descricao;
		this.cod_fisioterapeuta = cod_fisioterapeuta;
		this.nome_fisioterapeuta = nome_fisioterapeuta;
	}
	
	/** GETTERS E SETTERS **/
	public Integer getCod_fisioterapeuta() {
		return cod_fisioterapeuta;
	}
	public void setCod_fisioterapeuta(Integer cod_fisioterapeuta) {
		this.cod_fisioterapeuta = cod_fisioterapeuta;
	}
	public Integer getCod_servico() {
		return cod_servico;
	}
	public void setCod_servico(Integer cod_servico) {
		this.cod_servico = cod_servico;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Float getValor() {
		return valor;
	}
	public void setValor(Float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getNome_fisioterapeuta() {
		return nome_fisioterapeuta;
	}
	public void setNome_fisioterapeuta(String nome_fisioterapeuta) {
		this.nome_fisioterapeuta = nome_fisioterapeuta;
	}
	
}
