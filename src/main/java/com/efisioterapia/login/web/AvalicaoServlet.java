package com.efisioterapia.login.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efisioterapia.login.bean.AvaliacaoBean;
import com.efisioterapia.login.database.AvaliacaoDAO;

@WebServlet(urlPatterns = { "/avaliacoes", "/inserirAvaliacao" })
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
			novaAvaliacao(request, response);
		} else {
			response.sendRedirect("/index.jsp");
		}
	}
	
	/** NOVO SERVIÇO **/
	protected void novaAvaliacao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// PARÂMETROS DO ARQUIVO "adicionarServico.jsp"
		// TESTANDO OS RECIMENTOS DOS PARÂMETROS
		System.out.println(request.getParameter("nome_paciente"));
		System.out.println(request.getParameter("nome_fisioterapeuta"));
		System.out.println(request.getParameter("dt_avaliacao"));
		System.out.println(request.getParameter("descricao"));
		// PARÂMETROS DO ARQUIVO "adicionarServico.jsp"
		// SETAR AS VARIÁVEIS DO SERVIÇO
		String nome_fisioterapeuta = request.getParameter("nome_fisioterapeuta");
		System.out.println(nome_fisioterapeuta + "Profissional");
		int id_profissional = avaliacaoDAO.getProfissionalId(nome_fisioterapeuta);
		System.out.println(id_profissional);
		/**
		servico.setNome(request.getParameter("nome"));
		servico.setValor(Float.parseFloat((request.getParameter("valor"))));
		servico.setDescricao(request.getParameter("descricao"));
		servico.setCod_fisioterapeuta(id_profissional);
		**/
		// INVOCAR O MÉTODO "inserirServico" PASSANDO O OBJETO DE SERVIÇO
		avaliacaoDAO.inserirAvaliacao(avaliacao);
		// REDIRECIONAR PARA O PAINEL PRINCIPAL
		response.sendRedirect("servicos");
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
