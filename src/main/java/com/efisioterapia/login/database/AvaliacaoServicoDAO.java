package com.efisioterapia.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.efisioterapia.login.bean.AvaliacaoServicoBean;
import com.efisioterapia.login.bean.ServicoBean;

public class AvaliacaoServicoDAO {
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
	
	/** CRUD READ **/
	public ArrayList<AvaliacaoServicoBean> listarAvaliacaoServico() {
		// CRIANDO OBJETO PARA ACESSAR A CLASSE 'AvaliacaoServicoBean'
		ArrayList<AvaliacaoServicoBean> avaliacaoservicos = new ArrayList<>();
		// VARIÁVEL QUE ARMAZENARÁ A QUERY SQL
		String read = "SELECT Fisioterapeuta.Nome AS \"FISIOTERAPEUTA\", Paciente.Nome AS \"PACIENTE\",\r\n"
				+ "        Avaliacao.Ficha_Avaliacao AS \"AVALIAÇÃO\", Servico.nome AS \"SERVIÇO\", Avaliacao_Servico.Quantidade AS \"QUANTIDADE\"\r\n"
				+ "    FROM Fisioterapeuta, Paciente, Avaliacao, Servico, Avaliacao_Servico\r\n"
				+ "    WHERE Paciente.Cod_Paciente = Avaliacao.Cod_Paciente\r\n"
				+ "    AND Avaliacao.Cod_Fisioterapeuta = Fisioterapeuta.Cod_Fisioterapeuta\r\n"
				+ "    AND Avaliacao.Cod_Paciente = Paciente.Cod_Paciente\r\n"
				+ "    AND Servico.Cod_Servico = Avaliacao_Servico.Cod_Servico\r\n"
				+ "    AND Avaliacao.Cod_Avaliacao = Avaliacao_Servico.Cod_Avaliacao\r\n"
				+ "    ORDER BY 1,2,3";
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
				String fisioterapeuta = rs.getString(1);
				String paciente = rs.getString(2);
				String avaliacao = rs.getString(3);
				String servico = rs.getString(4);
				Integer quantidade = rs.getInt(5);
				// POPULANDO O ARRAYLIST
				avaliacaoservicos.add(new AvaliacaoServicoBean());
				
				System.out.println(fisioterapeuta);
				System.out.println(paciente);
				System.out.println(avaliacao);
				System.out.println(servico);
				System.out.println(quantidade);
			}
			// FECHANDO A CONEXÃO COM O BANCO DE DADOS
			con.close();
			return avaliacaoservicos;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
