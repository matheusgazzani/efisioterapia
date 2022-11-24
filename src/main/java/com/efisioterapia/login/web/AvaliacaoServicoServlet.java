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
import com.efisioterapia.login.bean.AvaliacaoServicoBean;
import com.efisioterapia.login.database.AvaliacaoDAO;
import com.efisioterapia.login.database.AvaliacaoServicoDAO;

@WebServlet(urlPatterns = { "/avaliacaoservicos" })
public class AvaliacaoServicoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// CRIAÇÃO DO OBJETO PACIENTE
	// ACESSAR OS MÉTODOS PÚBLICOS DA CLASSE
	AvaliacaoServicoDAO avaliacaoServicoDAO = new AvaliacaoServicoDAO();
	AvaliacaoServicoBean avaliacaoServico = new AvaliacaoServicoBean();

	public AvaliacaoServicoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/avaliacaoservicos")) {
			avaliacaoservicos(request, response);
		} else {
			response.sendRedirect("/painel.jsp");
		}
	}

	protected void avaliacaoservicos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// OBJETO QUE IRÁ RECEBER OS DADOS DO "AVALIACAOSERVICOBEAN"
		ArrayList<AvaliacaoServicoBean> lista = avaliacaoServicoDAO.listarAvaliacaoServico();

		// ENCAMINHAR A LISTA AO ARQUIVO 'Sessoes.jsp'
		request.setAttribute("avaliacaoservicos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("Sessoes.jsp");
		rd.forward(request, response);
		
		System.out.println(lista);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
