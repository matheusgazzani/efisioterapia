package com.efisioterapia.login.bean;

public class AvaliacaoServicoBean {
	/** CHAVES PRIMÁRIAS E CHAVES ESTRANGEIRAS **/
	/** ATRIBUTOS QUE VEM DE UM SELECT AVANÇADO **/
	/** RELAÇÃO N * N **/
	private Integer cod_avaliacao;
	private Integer cod_servico;
	private Integer quantidade;
	private Float valor_total;
	private String fisioterapeuta;
	private String paciente;
	private String avaliacao;
	private String servico;
	
	/** GETTERS E SETTERS DE TODOS ATRIBUTOS **/
	public Integer getCod_avaliacao() {
		return cod_avaliacao;
	}
	public void setCod_avaliacao(Integer cod_avaliacao) {
		this.cod_avaliacao = cod_avaliacao;
	}
	public Integer getCod_servico() {
		return cod_servico;
	}
	public void setCod_servico(Integer cod_servico) {
		this.cod_servico = cod_servico;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Float getValor_total() {
		return valor_total;
	}
	public void setValor_total(Float valor_total) {
		this.valor_total = valor_total;
	}
	public String getFisioterapeuta() {
		return fisioterapeuta;
	}
	public void setFisioterapeuta(String fisioterapeuta) {
		this.fisioterapeuta = fisioterapeuta;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(String avaliacao) {
		this.avaliacao = avaliacao;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	
	/** CONSTRUTORES **/
	public AvaliacaoServicoBean(Integer cod_avaliacao, Integer cod_servico, Integer quantidade, Float valor_total,
			String fisioterapeuta, String paciente, String avaliacao, String servico) {
		super();
		this.cod_avaliacao = cod_avaliacao;
		this.cod_servico = cod_servico;
		this.quantidade = quantidade;
		this.valor_total = valor_total;
		this.fisioterapeuta = fisioterapeuta;
		this.paciente = paciente;
		this.avaliacao = avaliacao;
		this.servico = servico;
	}
	
	public AvaliacaoServicoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
