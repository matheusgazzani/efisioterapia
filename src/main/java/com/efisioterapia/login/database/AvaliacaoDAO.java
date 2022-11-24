package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.efisioterapia.login.bean.AvaliacaoBean;
import com.efisioterapia.login.bean.ProfissionalBean;

public class AvaliacaoDAO {

	/** PARÂMETROS DE CONEXÃO **/
	/*
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/efisioterapia?useTimezone=true&serverTimezone=UTC&useSSL=false";
	private String user = "root";
	private String password = "root";
	*/
	
	private String jdbcURL = "jdbc:postgresql://localhost:5432/efisioterapiatest?useSSL=false";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "postgres";

	/** MÉTODO DE CONEXÃO PADRÃO **/
	/*
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	*/
	
	/** MÉTODO DE CONEXÃO PADRÃO **/
	private Connection conectar() {
		Connection con = null;

		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** MÉTODO SÓ PARA TESTAR CONEXÃO **/
	public void testeConexao() {
		try {
			Connection con = conectar();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	/** CRUD CREATE **/
	/**
	public void inserirProfissional(ProfissionalBean profissional) {
		String query = "INSERT INTO profissional (Nome, Telefone, DT_Nascimento, Email, Sexo, CREFITO, Especialidade, CPF) VALUES (?,?,?,?,?,?,?,?)";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(query);
			// SUBSTITUIR OS PARÂMETROS PELO CONTEÚDOS DAS VARIÁVEIS
			pst.setString(1, profissional.getNome());
			pst.setString(2, profissional.getTelefone());
			pst.setString(3, profissional.getDt_nascimento());
			pst.setString(4, profissional.getEmail());
			pst.setString(5, profissional.getSexo());
			pst.setString(6, profissional.getCrefito());
			pst.setString(7, profissional.getEspecialidade());
			pst.setString(8, profissional.getCpf());
			// EXECUTAR A QUERY
			pst.executeUpdate();
			// ENCERRAR A CONEXÃO COM O BANCO DE DADOS
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<AvaliacaoBean> listarAvaliacoes() {
		// CRIANDO OBJETO PARA ACESSAR A CLASSE 'AvaliacaoBean'
		ArrayList<AvaliacaoBean> avaliacoes = new ArrayList<>();
		// VARIÁVEL QUE ARMAZENARÁ A QUERY SQL
		// CONSULTA FEITA NA MÃO
		// FUTURAMENTE ADICIONAR UMA VIEW
		String read = "SELECT Avaliacao.Cod_Avaliacao AS \"ID\", Fisioterapeuta.Nome AS \"FISIOTERAPEUTA\", Paciente.Nome AS \"PACIENTE\",\r\n"
				+ "        Avaliacao.Ficha_Avaliacao AS \"AVALIAÇÃO\", Avaliacao.Dt_Avaliacao AS \"DATA\"\r\n"
				+ "    FROM Fisioterapeuta, Paciente, Avaliacao\r\n"
				+ "    WHERE Paciente.Cod_Paciente = Avaliacao.Cod_Paciente\r\n"
				+ "    AND Fisioterapeuta.Cod_Fisioterapeuta = Avaliacao.Cod_Fisioterapeuta";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(read);
			// ARMAZENAR O RETORNO DO BANCO DE DADOS TEMPORARIAMENTE
			ResultSet rs = pst.executeQuery();
			// LAÇO EXECUTADO ENQUANTO HOUVER AVALIAÇÕES
			while (rs.next()) {
				// VARIÁVEIS QUE RECEBEM DADOS DO BANCO
				Integer cod_avaliacao = rs.getInt(1);
				String nome_fisioterapeuta = rs.getString(2);
				String nome_paciente = rs.getString(3);
				String ficha_avaliacao = rs.getString(4);
				String dt_avaliacao = rs.getString(5);
				// POPULANDO O ARRAYLIST
				avaliacoes.add(new AvaliacaoBean(cod_avaliacao, nome_fisioterapeuta, nome_paciente, ficha_avaliacao, dt_avaliacao, cod_avaliacao, cod_avaliacao));
				
				System.out.println(cod_avaliacao);
				System.out.println(nome_fisioterapeuta);
				System.out.println(nome_paciente);
				System.out.println(ficha_avaliacao);
				System.out.println(dt_avaliacao);
			}
			// FECHANDO A CONEXÃO COM O BANCO DE DADOS
			con.close();
			return avaliacoes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	/**
	/** CRUD UPDATE | EDITAR **/
	// SELECIONAR O PROFISSIONAL
	/**
	public void selecionarProfissional(ProfissionalBean profissional) {
		String read2 = "SELECT * FROM profissional WHERE Cod_Profissional = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, profissional.getCod_fisioterapeuta());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// SETAR AS VARIÁVEIS DO PROFISSIONALBEAN
				profissional.setCod_fisioterapeuta(rs.getInt(1));
				profissional.setNome(rs.getString(2));
				profissional.setTelefone(rs.getString(3));
				profissional.setDt_nascimento(rs.getString(4));
				profissional.setEmail(rs.getString(5));
				profissional.setSexo(rs.getString(6));
				profissional.setCrefito(rs.getString(7));
				profissional.setEspecialidade(rs.getString(8));
				profissional.setCpf(rs.getString(9));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// EDITAR O PROFISSIONAL
	/**
	public void alterarProfissional(ProfissionalBean profissional) {
		String create = "UPDATE profissional SET Nome=?,Telefone=?,DT_Nascimento=?,Email=?,Sexo=?,CREFITO=?,Especialidade=?,CPF=? WHERE cod_profissional = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, profissional.getNome());
			pst.setString(2, profissional.getTelefone());
			pst.setString(3, profissional.getDt_nascimento());
			pst.setString(4, profissional.getEmail());
			pst.setString(5, profissional.getSexo());
			pst.setString(6, profissional.getCrefito());
			pst.setString(7, profissional.getEspecialidade());
			pst.setString(8, profissional.getCpf());
			pst.setInt(9, profissional.getCod_fisioterapeuta());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	/**
	public void deletarProfissional(ProfissionalBean profissional) {
		String delete = "DELETE FROM profissional WHERE Cod_Profissional = ?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, profissional.getCod_fisioterapeuta());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	**/
	
}
