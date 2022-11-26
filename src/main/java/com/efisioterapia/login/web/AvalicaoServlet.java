package com.efisioterapia.login.web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efisioterapia.login.bean.AvaliacaoBean;
import com.efisioterapia.login.database.AvaliacaoDAO;

@WebServlet(urlPatterns = { "/avaliacoes", "/inserirAvaliacao", "/deleteAvaliacao" })
public class AvalicaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// CRIAÇÃO DO OBJETO PACIENTE
	// ACESSAR OS MÉTODOS PÚBLICOS DA CLASSE
	AvaliacaoDAO avaliacaoDAO = new AvaliacaoDAO();
	AvaliacaoBean avaliacao = new AvaliacaoBean();

	public AvalicaoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/avaliacoes")) {
			avaliacoes(request, response);
		} else if (action.equals("/inserirAvaliacao")) {
			try {
				novaAvaliacao(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/deleteAvaliacao")) {
			removerAvaliacao(request, response);
		} else {
			response.sendRedirect("/index.jsp");
		}
	}

	/** NOVA AVALIAÇÃO 
	 * @throws ParseException **/
	protected void novaAvaliacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		// PARÂMETROS DO ARQUIVO "adicionarAvaliacao.jsp"
		// TESTANDO OS RECIMENTOS DOS PARÂMETROS
		System.out.println(request.getParameter("nome_paciente"));
		System.out.println(request.getParameter("nome_fisioterapeuta"));
		System.out.println(request.getParameter("dt_avaliacao"));
		System.out.println(request.getParameter("descricao"));
		// PARÂMETROS DO ARQUIVO "adicionarAvaliacao.jsp"
		// SETAR AS VARIÁVEIS DA AVALIAÇÃO
		String nome_fisioterapeuta = request.getParameter("nome_fisioterapeuta");
		String nome_paciente = request.getParameter("nome_paciente");
		System.out.println(nome_paciente + " Paciente");
		System.out.println(nome_fisioterapeuta + " Profissional");
		int id_paciente = avaliacaoDAO.getPacienteId(nome_paciente);
		int id_profissional = avaliacaoDAO.getProfissionalId(nome_fisioterapeuta);
		System.out.println(id_paciente);
		System.out.println(id_profissional);
		// SETAR OS PARÂMETROS NO BANCO DE DADOS
		Date dt_avaliacao = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("dt_avaliacao"));
		avaliacao.setDt_avaliacao(dt_avaliacao);
		avaliacao.setFicha_avaliacao(request.getParameter("ficha_avaliacao"));
		avaliacao.setCod_paciente(id_paciente);
		avaliacao.setCod_fisioterapeuta(id_profissional);
		// INVOCAR O MÉTODO "inserirAvaliacao" PASSANDO O OBJETO DE AVALIAÇÃO
		avaliacaoDAO.inserirAvaliacao(avaliacao);
		// REDIRECIONAR PARA O PAINEL PRINCIPAL
		response.sendRedirect("avaliacoes");
	}

	/** LISTAR AVALIAÇÕES **/
	protected void avaliacoes(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// OBJETO QUE IRÁ RECEBER OS DADOS DO "AVALIACAOBEAN"
		ArrayList<AvaliacaoBean> lista = avaliacaoDAO.listarAvaliacoes();

		// ENCAMINHAR A LISTA AO ARQUIVO 'exibirConsultas.jsp'
		request.setAttribute("avaliacoes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("exibirConsultas.jsp");
		rd.forward(request, response);
	}

	/** REMOVER AVALIAÇÃO **/
	protected void removerAvaliacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DA AVALIAÇÃO
		Integer cod_avaliacao = Integer.parseInt(request.getParameter("cod_avaliacao"));
		// SETAR A VARIÁVEL COD_AVALIACAO DA AVALIAÇÃO
		avaliacao.setCod_avaliacao(cod_avaliacao);
		// EXECUTAR O MÉTODO deletarAvaliacao
		avaliacaoDAO.deletarAvaliacao(avaliacao);
		// REDIRECIONAR PARA O DOCUMENTO exibirConsultas.jsp (COM ATUALIZAÇÕES DO
		// BANCO DE DADOS)
		response.sendRedirect("avaliacoes");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
