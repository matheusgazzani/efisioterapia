package com.efisioterapia.login.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.efisioterapia.login.bean.ServicoBean;
import com.efisioterapia.login.database.ServicosDAO;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet(urlPatterns = { "/servicos", "/inserirServico", "/selectServico", "/updateServico", "/deleteServico",
		"/reportServicos" })
public class ServicosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// CRIAÇÃO DE OBJETO SERVICOS
	// ACESSAR OS MÉTODOS PÚBLICOS DA CLASSE
	ServicosDAO servicosDao = new ServicosDAO();
	ServicoBean servico = new ServicoBean();

	public ServicosServlet() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/servicos")) {
			listarServicos(request, response);
		} else if (action.equals("/inserirServico")) {
			novoServico(request, response);
		} else if (action.equals("/selectServico")) {
			editarServico(request, response);
		} else if (action.equals("/updateServico")) {
			editarServicoTwo(request, response);
		} else if (action.equals("/deleteServico")) {
			removerServico(request, response);
		} else if (action.equals("/reportServicos")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("/painel.jsp");
		}

	}

	/** NOVO SERVIÇO **/
	protected void novoServico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// PARÂMETROS DO ARQUIVO "adicionarServico.jsp"
		// TESTANDO OS RECIMENTOS DOS PARÂMETROS
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("valor"));
		System.out.println(request.getParameter("descricao"));
		System.out.println(request.getParameter("nome_profissional"));
		// PARÂMETROS DO ARQUIVO "adicionarServico.jsp"
		// SETAR AS VARIÁVEIS DO SERVIÇO
		String nome_profissional = request.getParameter("nome_profissional");
		System.out.println(nome_profissional + "Profissional");
		int id_profissional = servicosDao.getProfissionalId(nome_profissional);
		System.out.println(id_profissional);
		servico.setNome(request.getParameter("nome"));
		servico.setValor(Float.parseFloat((request.getParameter("valor"))));
		servico.setDescricao(request.getParameter("descricao"));
		servico.setCod_fisioterapeuta(id_profissional);
		// INVOCAR O MÉTODO "inserirServico" PASSANDO O OBJETO DE SERVIÇO
		servicosDao.inserir(servico);
		// REDIRECIONAR PARA O PAINEL PRINCIPAL
		response.sendRedirect("servicos");
	}

	/** LISTAR SERVIÇO **/
	protected void listarServicos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// OBJETO QUE IRÁ RECEBER OS DADOS DO "SERVICOSBEAN"
		ArrayList<ServicoBean> lista = servicosDao.listarServicos();

		System.out.println(lista);

		request.setAttribute("servicos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("Servicos.jsp");
		rd.forward(request, response);
	}

	/** EDITAR SERVIÇO | SELECIONAR SERVIÇO **/
	protected void editarServico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DO SERVICO QUE SERÁ EDITADO
		Integer cod_servico = Integer.parseInt(request.getParameter("cod_servico"));
		System.out.println(cod_servico);
		// SETAR A VARIÁVEL ServicoBean
		servico.setCod_servico(cod_servico);
		// EXECUTAR O MÉTODO selecionarServico (DAO)
		servicosDao.selecionarServico(servico);
		// SETAR OS ATRIBUTOS DO FORMULÁRIO COM O CONTEÚDO SERVICOBEAN
		request.setAttribute("cod_servico", servico.getCod_servico());
		;
		request.setAttribute("nome", servico.getNome());
		request.setAttribute("valor", servico.getValor());
		request.setAttribute("descricao", servico.getDescricao());
		request.setAttribute("nome_fisioterapeuta", servico.getNome_fisioterapeuta());
		// ENCAMINHAR AO DOCUMENTO EDITARSERVICO.JSP
		RequestDispatcher rd = request.getRequestDispatcher("editarServico.jsp");
		rd.forward(request, response);
	}

	/** EDITAR SERVIÇO | SALVAR MUDANÇAS **/
	protected void editarServicoTwo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// SETAR AS VARIÁVEIS SERVICOBEAN
		servico.setCod_servico(Integer.parseInt(request.getParameter("cod_servico")));
		servico.setNome(request.getParameter("nome"));
		servico.setDescricao(request.getParameter("descricao"));
		servico.setNome_fisioterapeuta(request.getParameter("nome_fisioterapeuta"));
		// EXECUTAR O MÉTODO alterarServico
		servicosDao.alterarServico(servico);
		// REDIRECIONAR PARA O DOCUMENTO Servicos.jsp (COM ATUALIZAÇÕES DO BANCO
		// DE DADOS)
		response.sendRedirect("servicos");
	}

	/** REMOVER SERVIÇO **/
	protected void removerServico(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// RECEBIMENTO DO ID DO SERVIÇO
		Integer cod_servico = Integer.parseInt(request.getParameter("cod_servico"));
		// SETAR A VARIÁVEL COD_SERVICO DO SERVICOBEAN
		servico.setCod_servico(cod_servico);
		// EXECUTAR O MÉTODO deletarServico
		servicosDao.deletarServico(servico);
		// REDIRECIONAR PARA O DOCUMENTO Servicos.jsp (COM ATUALIZAÇÕES DO BANCO DE
		// DADOS)
		response.sendRedirect("servicos");
	}

	/** GERAR RELATÓRIO DE TODOS OS SERVIÇOS EM PDF **/
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// TIPO DE CONTEÚDO
			response.setContentType("apllication/pdf");
			// NOME DO DOCUMENTO
			response.addHeader("Content-Disposition", "inline; filename=" + "servicos.pdf");
			// CRIAR O DOCUMENTO
			PdfWriter.getInstance(documento, response.getOutputStream());
			// ABRIR O DOCUMENTO
			documento.open();
			documento.add(new Paragraph("RELATÓRIO DE SERVIÇOS: "));
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
			// CRIAR UMA TABELA
			PdfPTable tabela = new PdfPTable(2);
			// CABEÇALHO
			PdfPCell coluna1 = new PdfPCell(new Paragraph("SERVIÇO"));
			PdfPCell coluna2 = new PdfPCell(new Paragraph("DESCRIÇÃO"));
			tabela.addCell(coluna1);
			tabela.addCell(coluna2);
			// POPULAR A TABELA COM OS SERVIÇOS
			ArrayList<ServicoBean> lista = servicosDao.listarServicos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getDescricao());
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
