package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.efisioterapia.login.bean.AvaliacaoBean;
import com.efisioterapia.login.bean.ProfissionalBean;
import com.efisioterapia.login.bean.ServicoBean;

public class AvaliacaoDAO {

	private String jdbcURL = "jdbc:postgresql://localhost:5432/efisioterapiatest?useSSL=false";
	private String jdbcUsername = "postgres";
	private String jdbcPassword = "postgres";

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

	// ** CRUD - CREATE PEGAR O ID DO PROFISSIONAL **/
	public int getProfissionalId(String nome_profissional) {
		Integer idprofissional = 0;
		String select = "SELECT fisioterapeuta.cod_fisioterapeuta, fisioterapeuta.nome AS \"NOME_PROFISSIONAL\" FROM fisioterapeuta WHERE fisioterapeuta.nome = ?";
		try (Connection connection = conectar();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {
			preparedStatement.setString(1, nome_profissional);
			System.out.println(nome_profissional);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				idprofissional = rs.getInt("cod_fisioterapeuta");
				System.out.println(idprofissional + "----------");

			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return idprofissional;
	}

	// ** CRUD - CREATE PEGAR O ID DO PACIENTE **/
	public int getPacienteId(String nome_paciente) {
		Integer idpaciente = 0;
		String select = "SELECT paciente.cod_paciente, paciente.nome AS \"NOME_PACIENTE\" FROM paciente WHERE paciente.nome = ?";
		try (Connection connection = conectar();
				PreparedStatement preparedStatement = connection.prepareStatement(select)) {
			preparedStatement.setString(1, nome_paciente);
			System.out.println(nome_paciente);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				idpaciente = rs.getInt("cod_paciente");
				System.out.println(idpaciente + "----------");

			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return idpaciente;
	}

	/** CRUD CREATE **/
	public void inserirAvaliacao(AvaliacaoBean avaliacao) {
		String query = "INSERT INTO avaliacao (dt_avaliacao, ficha_avaliacao, cod_paciente, cod_fisioterapeuta) VALUES (?,?,?,?)";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(query);
			// SUBSTITUIR OS PARÂMETROS PELO CONTEÚDOS DAS VARIÁVEIS
			java.sql.Date data = new java.sql.Date(avaliacao.getDt_avaliacao().getTime());
			pst.setDate(1, data);
			pst.setString(2, avaliacao.getFicha_avaliacao());
			pst.setInt(3, avaliacao.getCod_paciente());
			pst.setInt(4, avaliacao.getCod_fisioterapeuta());
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
				Date dt_avaliacao = rs.getDate(5);
				// POPULANDO O ARRAYLIST
				avaliacoes.add(new AvaliacaoBean(cod_avaliacao, nome_fisioterapeuta, nome_paciente, ficha_avaliacao,
						dt_avaliacao, cod_avaliacao, cod_avaliacao));
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
	// SELECIONAR AVALIAÇÃO
	public void selecionarAvaliacao(AvaliacaoBean avaliacao) {
		String read2 = "SELECT avaliacao.cod_avaliacao, avaliacao.dt_avaliacao, avaliacao.ficha_avaliacao, fisioterapeuta.nome, paciente.nome FROM Avaliacao, Fisioterapeuta, Paciente \r\n"
				+ "WHERE Avaliacao.cod_avaliacao = ?"
				+ "AND Fisioterapeuta.cod_fisioterapeuta = Avaliacao.cod_fisioterapeuta\r\n"
				+ "AND Avaliacao.cod_paciente = Paciente.cod_paciente";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, avaliacao.getCod_avaliacao());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// SETAR AS VARIÁVEIS DO PROFISSIONALBEAN
				avaliacao.setCod_avaliacao(rs.getInt(1));
				avaliacao.setDt_avaliacao(rs.getDate(2));
				avaliacao.setFicha_avaliacao(rs.getString(3));
				avaliacao.setNome_fisioterapeuta(rs.getString(4));
				avaliacao.setNome_paciente(rs.getString(5));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// EDITAR AVALIAÇÃO
	public void alterarProfissional(ProfissionalBean profissional) {
		String create = "UPDATE fisioterapeuta SET Nome=?,Telefone=?,DT_Nascimento=?,Email=?,Sexo=?,CREFITO=?,Especialidade=?,CPF=? WHERE cod_fisioterapeuta = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, profissional.getNome());
			pst.setString(2, profissional.getTelefone());
			java.sql.Date data = new java.sql.Date(profissional.getDt_nascimento().getTime());
			pst.setDate(3, data);
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
	public void deletarAvaliacao(AvaliacaoBean avaliacao) {
		String delete = "DELETE FROM avaliacao WHERE cod_avaliacao = ?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, avaliacao.getCod_avaliacao());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
