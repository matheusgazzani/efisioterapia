package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.efisioterapia.login.bean.ServicoBean;
import com.efisioterapia.login.bean.ProfissionalBean;


public class ServicosDAO {
	
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
	
	/** CRUD CREATE **/
	public void inserir(ServicoBean servico) {
		String query = "INSERT INTO servico (Nome, Valor, Descricao, Cod_Fisioterapeuta) VALUES (?,?,?,?)";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(query);
			// SUBSTITUIR OS PARÂMETROS PELO CONTEÚDOS DAS VARIÁVEIS
			pst.setString(1, servico.getNome());
			pst.setFloat(2, servico.getValor());
			pst.setString(3, servico.getDescricao());
			pst.setInt(4, servico.getCod_fisioterapeuta());
			// EXECUTAR A QUERY
			pst.executeUpdate();
			// ENCERRAR A CONEXÃO COM O BANCO DE DADOS
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** OPÇÃO PARA SELECIONAR UM PROFISSIONAL | CHAVE ESTRANGEIRA **/
	public void selecionarProfissional(ServicoBean servico) {
		String profissional = "SELECT fisioterapeuta.nome AS FROM fisioterapeuta";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(profissional);
			pst.setInt(1, servico.getCod_servico());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// SETAR AS VARIÁVEIS DO SERVICOBEAN
				servico.setNome_fisioterapeuta(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** LISTAR TODOS OS PROFISSIONAIS CADASTRADOS NO BD **/
	public List<ProfissionalBean> listProfissional() {
		List<ProfissionalBean> profissionais = new ArrayList<>();
		String read = "SELECT fisioterapeuta.cod_fisioterapeuta, fisioterapeuta.nome AS \"nome\" FROM fisioterapeuta";
		try (Connection connection = conectar();
				PreparedStatement preparedStatement = connection.prepareStatement(read)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("cod_fisioterapeuta");
				String nome = rs.getString("nome");
				profissionais.add(new ProfissionalBean(id, nome));
				System.out.println(id);
				System.out.println(nome);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return profissionais;
	}

	/** CRUD READ **/
	public ArrayList<ServicoBean> listarServicos() {
		// CRIANDO OBJETO PARA ACESSAR A CLASSE 'ServicoBean'
		ArrayList<ServicoBean> servicos = new ArrayList<>();
		// VARIÁVEL QUE ARMAZENARÁ A QUERY SQL
		String read = "SELECT servico.cod_servico, servico.nome,servico.valor, servico.descricao, fisioterapeuta.nome\r\n"
				+ "    FROM Servico, Fisioterapeuta\r\n"
				+ "    WHERE Servico.cod_fisioterapeuta = Fisioterapeuta.Cod_Fisioterapeuta";
		try {
			// ABRIR CONEXÃO COM O BANCO DE DADOS
			Connection con = conectar();
			// PREPARAR A QUERY PARA EXECUTAR NO BANCO DE DADOS
			PreparedStatement pst = con.prepareStatement(read);
			// ARMAZENAR O RETORNO DO BANCO DE DADOS TEMPORARIAMENTE
			ResultSet rs = pst.executeQuery();
			// LAÇO EXECUTADO ENQUANTO HOUVER SERVIÇOS
			while (rs.next()) {
				// VARIÁVEIS QUE RECEBEM DADOS DO BANCO
				Integer cod_servico = rs.getInt(1);
				String nome = rs.getString(2);
				Float valor = rs.getFloat(3);
				String descricao = rs.getString(4);
				String nome_fisioterapeuta = rs.getString(5);
				// POPULANDO O ARRAYLIST
				servicos.add(new ServicoBean(cod_servico, nome, valor, descricao, nome_fisioterapeuta));
			}
			// FECHANDO A CONEXÃO COM O BANCO DE DADOS
			con.close();
			return servicos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	/**
	/** CRUD UPDATE | EDITAR **/
	/** SELECIONAR O SERVIÇO **/
	public void selecionarServico(ServicoBean servico) {
		String read2 = "SELECT servico.cod_servico, servico.nome,servico.valor, servico.descricao, fisioterapeuta.cod_fisioterapeuta ,fisioterapeuta.nome FROM Servico, Fisioterapeuta\r\n"
				+ "WHERE Servico.cod_fisioterapeuta = ?"
				+ "AND Fisioterapeuta.cod_fisioterapeuta = Servico.cod_fisioterapeuta";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setInt(1, servico.getCod_servico());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				// SETAR AS VARIÁVEIS DO SERVICOBEAN
				servico.setCod_servico(rs.getInt(1));
				servico.setNome(rs.getString(2));
				servico.setValor(rs.getFloat(3));
				servico.setDescricao(rs.getString(4));
				servico.setCod_fisioterapeuta(rs.getInt(5));
				servico.setNome_fisioterapeuta(rs.getString(6));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** EDITAR O SERVIÇO **/
	/** SALVAR O SERVIÇO **/
	public void alterarServico(ServicoBean servico) {
		String create = "UPDATE servico SET nome=?,valor=?,descricao=?,cod_fisioterapeuta=? WHERE cod_servico = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, servico.getNome());
			pst.setFloat(2, servico.getValor());
			pst.setString(3, servico.getDescricao());			
			pst.setInt(4, servico.getCod_fisioterapeuta());
			pst.setInt(5, servico.getCod_servico());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarServico(ServicoBean servico) {
		String delete = "DELETE FROM servico WHERE cod_servico = ?;";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setInt(1, servico.getCod_servico());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}
