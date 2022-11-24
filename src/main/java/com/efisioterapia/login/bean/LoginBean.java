package com.efisioterapia.login.bean;

import java.io.Serializable;

public class LoginBean implements Serializable{
	
    private static final long serialVersionUID = 1L;
    private String login;
    private String senha;
    //private String ativo;
    //private String perfil;
    //private String DT_Criacao;
	
    public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
    
}
