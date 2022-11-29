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

import com.efisioterapia.login.bean.PacienteBean;
import com.efisioterapia.login.database.PacienteDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet(urlPatterns = { "/paciente", "/insert", "/selectPaciente", "/updatePaciente", "/deletePaciente",
		"/reportPacientes" })
public class PacienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// CRIAÇÃO DO OBJETO PACIENTE
	// ACESSAR OS MÉTODOS PÚBLICOS DA CLASSE
	PacienteDAO dao = new PacienteDAO();
	PacienteBean paciente = new PacienteBean(null, null, null, null, null, null, null, null);

	public PacienteServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/paciente")) {
			paciente(request, response);
		} else if (action.equals("/insert")) {
			try {
				novoPaciente(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/selectPaciente")) {
			editarPaciente(request, response);
		} else if (action.equals("/updatePaciente")) {
			try {
				editarPacienteTwo(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equals("/deletePaciente")) {
			removerPaciente(request, response);
		} else if (action.equals("/reportPacientes")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("/index.jsp");
		}

	}

	// LISTAR PACIENTES
	protected void paciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// OBJETO QUE IRÁ RECEBER OS DADOS DO "PACIENTEBEAN"
		ArrayList<PacienteBean> lista = dao.listarPacientes();

		// ENCAMINHAR A LISTA AO ARQUIVO 'exibirPacientes.jsp'
		request.setAttribute("pacientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("exibirPacientes.jsp");
		rd.forward(request, response);
	}

	/** NOVO PACIENTE 
	 * @throws ParseException **/
	protected void novoPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		// TESTANDO O RECEBIMENTO DO FORMULÁRIO
		// PARÂMETROS DO ARQUIVO "cadastrarPaciente.jsp"
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("email"));
		System.out.println(request.getParameter("cpf"));
		System.out.println(request.getParameter("profissao"));
		System.out.println(request.getParameter("sexo"));
		System.out.println(request.getParameter("telefone"));

		// SETAR AS VARIÁVEIS DO PACIENTE
		paciente.setNome(request.getParameter("nome"));
		paciente.setEmail(request.getParameter("email"));
		paciente.setCpf(request.getParameter("cpf"));
		paciente.setProfissao(request.getParameter("profissao"));
		paciente.setSexo(request.getParameter("sexo"));
		paciente.setTelefone(request.getParameter("telefone"));
		Date dt_nascimento = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("nascimento"));
		paciente.setDt_nascimento(dt_nascimento);

		// INVOCAR O MÉTODO "inserirPaciente" passando o objeto de paciente
		dao.inserirPaciente(paciente);
		// REDIRECIONAR PARA O PAINEL PRINCIPAL
		response.sendRedirect("paciente");
	}

	/** EDITAR PACIENTE **/
	protected void editarPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DO CONTATO QUE SERÁ EDITADO
		int cod_paciente = Integer.parseInt(request.getParameter("cod_paciente"));
		System.out.println(cod_paciente);
		// SETAR A VARIÁVEL PacienteBean
		paciente.setCod_paciente(cod_paciente);
		// EXECUTAR O MÉTODO selecionarPaciente (DAO)
		dao.selecionarPaciente(paciente);
		// SETAR OS ATRIBUTOS DO FORMULÁRIO COM O CONTEÚDO PACIENTEBEAN
		request.setAttribute("cod_paciente", paciente.getCod_paciente());
		request.setAttribute("nome", paciente.getNome());
		request.setAttribute("dt_nascimento", paciente.getDt_nascimento());
		request.setAttribute("email", paciente.getEmail());
		request.setAttribute("profissao", paciente.getProfissao());
		request.setAttribute("sexo", paciente.getSexo());
		request.setAttribute("cpf", paciente.getCpf());
		request.setAttribute("telefone", paciente.getTelefone());
		// ENCAMINHAR AO DOCUMENTO EDITAR.JSP
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/** EDITAR PACIENTE 
	 * @throws ParseException **/
	protected void editarPacienteTwo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		// SETAR AS VARIÁVEIS PACIENTEBEAN
		paciente.setCod_paciente(Integer.parseInt(request.getParameter("cod_paciente")));
		paciente.setNome(request.getParameter("nome"));
		Date dt_nascimento = new SimpleDateFormat("yyyy-mm-dd").parse(request.getParameter("dt_nascimento"));
		paciente.setDt_nascimento(dt_nascimento);
		paciente.setEmail(request.getParameter("email"));
		paciente.setProfissao(request.getParameter("profissao"));
		paciente.setSexo(request.getParameter("sexo"));
		paciente.setCpf(request.getParameter("cpf"));
		paciente.setTelefone(request.getParameter("telefone"));
		// EXECUTAR O MÉTODO alterarPaciente
		dao.alterarPaciente(paciente);
		// REDIRECIONAR PARA O DOCUMENTO exibirPacientes.jsp (COM ATUALIZAÇÕES DO BANCO
		// DE DADOS)
		response.sendRedirect("paciente");
	}

	/** REMOVER PACIENTE **/
	protected void removerPaciente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DO PACIENTE
		int cod_paciente = Integer.parseInt(request.getParameter("cod_paciente"));
		// SETAR A VARIÁVEL COD_PACIENTE DO PACIENTEBEAN
		paciente.setCod_paciente(cod_paciente);
		// EXECUTAR O MÉTODO deletarPaciente
		dao.deletarPaciente(paciente);
		// REDIRECIONAR PARA O DOCUMENTO exibirPacientes.jsp (COM ATUALIZAÇÕES DO BANCO
		// DE DADOS)
		response.sendRedirect("paciente");
	}

	/** GERAR RELATÓRIO DE TODOS OS PACIENTES EM PDF **/
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// TIPO DE CONTEÚDO
			response.setContentType("apllication/pdf");
			// NOME DO DOCUMENTO
			response.addHeader("Content-Disposition", "inline; filename=" + "pacientes.pdf");
			// CRIAR O DOCUMENTO
			PdfWriter.getInstance(documento, response.getOutputStream());
			// ABRIR O DOCUMENTO
			documento.open();
			documento.add(new Paragraph("RELATÓRIO DE PACIENTES: "));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			// CRIAR UMA TABELA
			PdfPTable tabela = new PdfPTable(7);
			// CABEÇALHO
			PdfPCell coluna2 = new PdfPCell(new Paragraph("NOME"));
			PdfPCell coluna3 = new PdfPCell(new Paragraph("DT NASCIMENTO"));
			PdfPCell coluna4 = new PdfPCell(new Paragraph("E-MAIL"));
			PdfPCell coluna5 = new PdfPCell(new Paragraph("PROFISSÃO"));
			PdfPCell coluna6 = new PdfPCell(new Paragraph("SEXO"));
			PdfPCell coluna7 = new PdfPCell(new Paragraph("CPF"));
			PdfPCell coluna8 = new PdfPCell(new Paragraph("TEL"));
			tabela.addCell(coluna2);
			tabela.addCell(coluna3);
			tabela.addCell(coluna4);
			tabela.addCell(coluna5);
			tabela.addCell(coluna6);
			tabela.addCell(coluna7);
			tabela.addCell(coluna8);
			// POPULAR A TABELA COM OS PACIENTES
			ArrayList<PacienteBean> lista = dao.listarPacientes();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				//tabela.addCell(lista.get(i).getDt_nascimento());
				tabela.addCell(lista.get(i).getEmail());
				tabela.addCell(lista.get(i).getProfissao());
				tabela.addCell(lista.get(i).getSexo());
				tabela.addCell(lista.get(i).getCpf());
				tabela.addCell(lista.get(i).getTelefone());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			documento.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
