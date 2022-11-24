package com.efisioterapia.login.bean;

import java.util.Date;

public class AvaliacaoBean {
	private Integer cod_avaliacao;
	private String dt_avaliacao;
	private String ficha_avaliacao;
	// ** CHAVES ESTRANGEIRAS **//
	private Integer cod_paciente;
	private Integer cod_fisioterapeuta;
	private String nome_paciente;
	private String nome_fisioterapeuta;
	// ** FIM DAS CHAVES ESTRANGEIRAS **//

	public AvaliacaoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AvaliacaoBean(Integer cod_avaliacao, String nome_fisioterapeuta, String nome_paciente, String ficha_avaliacao, String dt_avaliacao, Integer cod_paciente,
			Integer cod_fisioterapeuta) {
		super();
		this.cod_avaliacao = cod_avaliacao;
		this.dt_avaliacao = dt_avaliacao;
		this.ficha_avaliacao = ficha_avaliacao;
		this.cod_paciente = cod_paciente;
		this.cod_fisioterapeuta = cod_fisioterapeuta;
		this.nome_paciente = nome_paciente;
		this.nome_fisioterapeuta = nome_fisioterapeuta;
	}

	// ** GETTERS E SETTERS **//
	public Integer getCod_avaliacao() {
		return cod_avaliacao;
	}

	public void setCod_avaliacao(Integer cod_avaliacao) {
		this.cod_avaliacao = cod_avaliacao;
	}

	public String getDt_avaliacao() {
		return dt_avaliacao;
	}

	public void setDt_avaliacao(String dt_avaliacao) {
		this.dt_avaliacao = dt_avaliacao;
	}

	public String getFicha_avaliacao() {
		return ficha_avaliacao;
	}

	public void setFicha_avaliacao(String ficha_avaliacao) {
		this.ficha_avaliacao = ficha_avaliacao;
	}

	// ** GETTERS E SETTERS DAS CHAVES ESTRANGEIRAS **//
	public Integer getCod_paciente() {
		return cod_paciente;
	}

	public void setCod_paciente(Integer cod_paciente) {
		this.cod_paciente = cod_paciente;
	}

	public Integer getCod_fisioterapeuta() {
		return cod_fisioterapeuta;
	}

	public void setCod_fisioterapeuta(Integer cod_fisioterapeuta) {
		this.cod_fisioterapeuta = cod_fisioterapeuta;
	}

	public String getNome_paciente() {
		return nome_paciente;
	}

	public void setNome_paciente(String nome_paciente) {
		this.nome_paciente = nome_paciente;
	}

	public String getNome_fisioterapeuta() {
		return nome_fisioterapeuta;
	}

	public void setNome_fisioterapeuta(String nome_fisioterapeuta) {
		this.nome_fisioterapeuta = nome_fisioterapeuta;
	}

}
