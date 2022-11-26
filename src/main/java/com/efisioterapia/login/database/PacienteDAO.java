package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.efisioterapia.login.bean.PacienteBean;

// CLASSE QUE FAZ CONEXÃO COM O BANCO DE DADOS
public class PacienteDAO {

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
	public void inserirPaciente(PacienteBean paciente) {
		String query = "INSERT INTO paciente (Nome, DT_Nascimento, Email, Profissao, Sexo, CPF, Telefone) VALUES (?,?,?,?,?,?,?)";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(query);
			// SUBSTITUIR OS PARÂMETROS PELO CONTEÚDOS DAS VARIÁVEIS
			pst.setString(1, paciente.getNome());
			java.sql.Date data = new java.sql.Date(paciente.getDt_nascimento().getTime());
			pst.setDate(2, data);
			pst.setString(3, paciente.getEmail());
			pst.setString(4, paciente.getProfissao());
			pst.setString(5, paciente.getSexo());
			pst.setString(6, paciente.getCpf());
			pst.setString(7, paciente.getTelefone());
			// EXECUTAR A QUERY
			pst.executeUpdate();
			// ENCERRAR A CONEXÃO COM O BANCO DE DADOS
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<PacienteBean> listarPacientes() {
		// CRIANDO OBJETO PARA ACESSAR A CLASSE 'PacienteBean'
		ArrayList<PacienteBean> pacientes = new ArrayList<>();
		// VARIÁVEL QUE ARMAZENARÁ A QUERY SQL
		String read = "SELECT * FROM v_lista_pacientes";
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
				Integer cod_paciente = rs.getInt(1);
				String nome = rs.getString(2);
				Date dt_nascimento = rs.getDate(3);
				String email = rs.getString(4);
				String profissao = rs.getString(5);
				String sexo = rs.getString(6);
				String cpf = rs.getString(7);
				String telefone = rs.getString(8);
			
				// POPULANDO O ARRAYLIST
				pacientes.add(new PacienteBean(cod_paciente, nome, dt_nascimento, email, profissao, sexo, cpf, telefone));
				
				/**
				 * TESTES COM CHAVE ESTRANGEIRA 
				 
				Integer cod_avaliacao = rs.getInt(1);
				Date dt_avaliacao = rs.getDate(2);
				String ficha_avaliacao = rs.getString(3);
				Integer cod_paciente1 = rs.getInt(4);
				Integer cod_fisioterapeuta = rs.getInt(5);
				
				System.out.println(cod_avaliacao);
				System.out.println(dt_avaliacao);
				System.out.println(ficha_avaliacao);
				System.out.println(cod_paciente1);
				System.out.println(cod_fisioterapeuta);
				
				pacientes.add(new PacienteBean(cod_avaliacao, dt_avaliacao, ficha_avaliacao, cod_paciente1, cod_fisioterapeuta));
				**/
			}
			
			// FECHANDO A CONEXÃO COM O BANCO DE DADOS
			con.close();
			return pacientes;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD UPDATE | EDITAR **/
	// SELECIONAR O PACIENTE
	public void selecionarPaciente(PacienteBean paciente) {
		String read2 = "SELECT * FROM paciente WHERE cod_paciente = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, paciente.getCod_paciente());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// SETAR AS VARIÁVEIS DO PACIENTEBEAN
				paciente.setCod_paciente(rs.getInt(1));
				paciente.setNome(rs.getString(2));
				paciente.setDt_nascimento(rs.getDate(3));
				paciente.setEmail(rs.getString(4));
				paciente.setProfissao(rs.getString(5));
				paciente.setSexo(rs.getString(6));
				paciente.setCpf(rs.getString(7));
				paciente.setTelefone(rs.getString(8));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	// EDITAR O PACIENTE
	public void alterarPaciente(PacienteBean paciente) {
		String create = "UPDATE paciente SET Nome=?,DT_Nascimento=?,Email=?,Profissao=?,Sexo=?,CPF=?,Telefone=? WHERE cod_paciente = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, paciente.getNome());
			java.sql.Date data = new java.sql.Date(paciente.getDt_nascimento().getTime());
			pst.setDate(2, data);
			pst.setString(3, paciente.getEmail());
			pst.setString(4, paciente.getProfissao());
			pst.setString(5, paciente.getSexo());
			pst.setString(6, paciente.getCpf());
			pst.setString(7, paciente.getTelefone());
			pst.setInt(8, paciente.getCod_paciente());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarPaciente(PacienteBean paciente) {
		String delete = "DELETE FROM paciente WHERE cod_paciente = ?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, paciente.getCod_paciente());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
