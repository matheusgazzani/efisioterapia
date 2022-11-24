package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.efisioterapia.login.bean.PacienteBean;
import com.efisioterapia.login.bean.ProfissionalBean;

public class ProfissionalDAO {
	/** PARÂMETROS DE CONEXÃO **/
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

	/** CRUD CREATE **/
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
	public ArrayList<ProfissionalBean> listarProfissionais() {
		// CRIANDO OBJETO PARA ACESSAR A CLASSE 'ProfissionalBean'
		ArrayList<ProfissionalBean> profissionais = new ArrayList<>();
		// VARIÁVEL QUE ARMAZENARÁ A QUERY SQL
		// SELECT * FROM DE UMA VIEW
		String read = "SELECT * FROM v_lista_profissionais";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(read);
			// ARMAZENAR O RETORNO DO BANCO DE DADOS TEMPORARIAMENTE
			ResultSet rs = pst.executeQuery();
			// LAÇO EXECUTADO ENQUANTO HOUVER PACIENTES
			while (rs.next()) {
				// VARIÁVEIS QUE RECEBEM DADOS DO BANCO
				Integer cod_profissional = rs.getInt(3);
				String nome = rs.getString(5);
				String telefone = rs.getString(1);
				String dt_nascimento = rs.getString(2);
				String email = rs.getString(4);
				String sexo = rs.getString(6);
				String crefito = rs.getString(7);
				String especialidade = rs.getString(8);
				String cpf = rs.getString(9);
				// POPULANDO O ARRAYLIST
				profissionais.add(new ProfissionalBean(cod_profissional, nome, telefone, dt_nascimento, email, sexo, crefito, especialidade, cpf));
			}
			// FECHANDO A CONEXÃO COM O BANCO DE DADOS
			con.close();
			return profissionais;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	/**
	/** CRUD UPDATE | EDITAR **/
	// SELECIONAR O PROFISSIONAL
	public void selecionarProfissional(ProfissionalBean profissional) {
		String read2 = "SELECT * FROM fisioterapeuta WHERE Cod_Fisioterapeuta = ?";
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
	public void alterarProfissional(ProfissionalBean profissional) {
		String create = "UPDATE fisioterapeuta SET Nome=?,Telefone=?,DT_Nascimento=?,Email=?,Sexo=?,CREFITO=?,Especialidade=?,CPF=? WHERE cod_fisioterapeuta = ?";
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
	public void deletarProfissional(ProfissionalBean profissional) {
		String delete = "DELETE FROM fisioterapeuta WHERE Cod_Fisioterapeuta = ?;";
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
	
}
