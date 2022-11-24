package com.efisioterapia.login.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efisioterapia.login.bean.PacienteBean;
import com.efisioterapia.login.bean.ProfissionalBean;
import com.efisioterapia.login.database.ProfissionalDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet(urlPatterns = { "/profissional", "/insertProfissional", "/selectProfissional", "/updateProfissional",
		"/deleteProfissional", "/reportProfissionais" })
public class ProfissionalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// CRIAÇÃO DO OBJETO PROFISSIONAL
	// ACESSAR OS MÉTODOS PÚBLICOS DA CLASSE
	ProfissionalDAO profissionalDao = new ProfissionalDAO();
	ProfissionalBean profissional = new ProfissionalBean();

	public ProfissionalServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/insertProfissional")) {
			novoProfissional(request, response);
		} else if (action.equals("/profissional")) {
			profissionais(request, response);
		} else if (action.equals("/selectProfissional")) {
			editarProfissional(request, response);
		} else if (action.equals("/updateProfissional")) {
			editarProfissionalTwo(request, response);
		} else if (action.equals("/deleteProfissional")) {
			removerProfissional(request, response);
		} else if (action.equals("/reportProfissionais")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("/painel.jsp");
		}

	}

	// NOVO PACIENTE
	protected void novoProfissional(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETAR AS VARIÁVEIS DO PROFISSIONAL
		profissional.setNome(request.getParameter("nome"));
		profissional.setTelefone(request.getParameter("telefone"));
		profissional.setDt_nascimento(request.getParameter("dt_nascimento"));
		profissional.setEmail(request.getParameter("email"));
		profissional.setSexo(request.getParameter("sexo"));
		profissional.setCrefito(request.getParameter("crefito"));
		profissional.setEspecialidade(request.getParameter("especialidade"));
		profissional.setCpf(request.getParameter("cpf"));
		// INVOCAR O MÉTODO "inserirProfissional" passando o objeto de profissional
		profissionalDao.inserirProfissional(profissional);
		// REDIRECIONAR PARA O PAINEL PRINCIPAL
		response.sendRedirect("profissional");
	}

	// LISTAR PROFISSIONAIS
	protected void profissionais(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// OBJETO QUE IRÁ RECEBER OS DADOS DO "PROFISSIONALBEAN"
		ArrayList<ProfissionalBean> lista = profissionalDao.listarProfissionais();

		// ENCAMINHAR A LISTA AO ARQUIVO 'exibirProfissionais.jsp'
		request.setAttribute("profissionais", lista);
		RequestDispatcher rd = request.getRequestDispatcher("exibirProfissionais.jsp");
		rd.forward(request, response);
	}

	// EDITAR PROFISSIONAL
	protected void editarProfissional(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DO CONTATO QUE SERÁ EDITADO
		Integer cod_fisioterapeuta = Integer.parseInt(request.getParameter("cod_fisioterapeuta"));
		System.out.println(cod_fisioterapeuta);
		// SETAR A VARIÁVEL ProfissionalBean
		profissional.setCod_fisioterapeuta(cod_fisioterapeuta);
		// EXECUTAR O MÉTODO selecionarProfissional (DAO)
		profissionalDao.selecionarProfissional(profissional);
		// SETAR OS ATRIBUTOS DO FORMULÁRIO COM O CONTEÚDO PROFISSIONALBEAN
		request.setAttribute("cod_fisioterapeuta", profissional.getCod_fisioterapeuta());
		request.setAttribute("nome", profissional.getNome());
		request.setAttribute("telefone", profissional.getTelefone());
		request.setAttribute("dt_nascimento", profissional.getDt_nascimento());
		request.setAttribute("email", profissional.getEmail());
		request.setAttribute("sexo", profissional.getSexo());
		request.setAttribute("crefito", profissional.getCrefito());
		request.setAttribute("especialidade", profissional.getEspecialidade());
		request.setAttribute("cpf", profissional.getCpf());
		// ENCAMINHAR AO DOCUMENTO EDITAR.JSP
		RequestDispatcher rd = request.getRequestDispatcher("editarProfissional.jsp");
		rd.forward(request, response);
	}

	protected void editarProfissionalTwo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETAR AS VARIÁVEIS PROFISSIONALBEAN
		profissional.setCod_fisioterapeuta(Integer.parseInt(request.getParameter("cod_fisioterapeuta")));
		profissional.setNome(request.getParameter("nome"));
		profissional.setTelefone(request.getParameter("telefone"));
		profissional.setDt_nascimento(request.getParameter("dt_nascimento"));
		profissional.setEmail(request.getParameter("email"));
		profissional.setSexo(request.getParameter("sexo"));
		profissional.setCrefito(request.getParameter("crefito"));
		profissional.setEspecialidade(request.getParameter("especialidade"));
		profissional.setCpf(request.getParameter("cpf"));
		// EXECUTAR O MÉTODO alterarProfissional
		profissionalDao.alterarProfissional(profissional);
		// REDIRECIONAR PARA O DOCUMENTO exibirProfissionais.jsp (COM ATUALIZAÇÕES DO
		// BANCO
		// DE DADOS)
		response.sendRedirect("profissional");
	}

	protected void removerProfissional(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DO PROFISSIONAL
		Integer cod_fisioterapeuta = Integer.parseInt(request.getParameter("cod_fisioterapeuta"));
		// SETAR A VARIÁVEL COD_PACIENTE DO PROFISSIONAL
		profissional.setCod_fisioterapeuta(cod_fisioterapeuta);
		// EXECUTAR O MÉTODO deletarProfissional
		profissionalDao.deletarProfissional(profissional);
		;
		// REDIRECIONAR PARA O DOCUMENTO exibirProfissionais.jsp (COM ATUALIZAÇÕES DO
		// BANCO DE DADOS)
		response.sendRedirect("profissional");
	}
	
	/** GERAR RELATÓRIO DE TODOS OS PROFISSIONAIS EM PDF **/
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// TIPO DE CONTEÚDO
			response.setContentType("apllication/pdf");
			// NOME DO DOCUMENTO
			response.addHeader("Content-Disposition", "inline; filename=" + "profissionais.pdf");
			// CRIAR O DOCUMENTO
			PdfWriter.getInstance(documento, response.getOutputStream());
			// ABRIR O DOCUMENTO
			documento.open();
			documento.add(new Paragraph("RELATÓRIO DE PROFISSIONAIS: "));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			// CRIAR UMA TABELA
			PdfPTable tabela = new PdfPTable(8);
			// CABEÇALHO
			PdfPCell coluna2 = new PdfPCell(new Paragraph("NOME"));
			PdfPCell coluna3 = new PdfPCell(new Paragraph("TELEFONE"));
			PdfPCell coluna4 = new PdfPCell(new Paragraph("NASCIMENTO"));
			PdfPCell coluna5 = new PdfPCell(new Paragraph("E-MAIL"));
			PdfPCell coluna6 = new PdfPCell(new Paragraph("SEXO"));
			PdfPCell coluna7 = new PdfPCell(new Paragraph("CREFITO"));
			PdfPCell coluna8 = new PdfPCell(new Paragraph("ESPECIALIDADE"));
			PdfPCell coluna9 = new PdfPCell(new Paragraph("CPF"));
			tabela.addCell(coluna2);
			tabela.addCell(coluna3);
			tabela.addCell(coluna4);
			tabela.addCell(coluna5);
			tabela.addCell(coluna6);
			tabela.addCell(coluna7);
			tabela.addCell(coluna8);
			tabela.addCell(coluna9);
			// POPULAR A TABELA COM OS PACIENTES
			ArrayList<ProfissionalBean> lista = profissionalDao.listarProfissionais();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getTelefone());
				tabela.addCell(lista.get(i).getDt_nascimento());
				tabela.addCell(lista.get(i).getEmail());
				tabela.addCell(lista.get(i).getSexo());
				tabela.addCell(lista.get(i).getCrefito());
				tabela.addCell(lista.get(i).getEspecialidade());
				tabela.addCell(lista.get(i).getCpf());
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
