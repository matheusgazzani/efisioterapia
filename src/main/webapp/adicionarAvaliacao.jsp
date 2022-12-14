<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.efisioterapia.login.web.AvalicaoServlet"%>
<%@ page import="com.efisioterapia.login.database.AvaliacaoDAO"%>
<%@ page import="com.efisioterapia.login.bean.AvaliacaoBean"%>
<%@ page import="com.efisioterapia.login.bean.ProfissionalBean"%>
<%@ page import="com.efisioterapia.login.bean.PacienteBean"%>
<%@ page import="java.util.List"%>
<%
ArrayList<ProfissionalBean> lista = (ArrayList<ProfissionalBean>) request.getAttribute("profissionais");	
%>
<%
ArrayList<PacienteBean> listapaciente = (ArrayList<PacienteBean>) request.getAttribute("pacientes");	
%>  	
<!DOCTYPE html>
<html lang="PT-BR">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Consultas | e-Fisioterapia</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Patua+One">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/marcasConsultas.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<style>
<%@include file="css/marcarConsultas.css" %>
body {
	background-image:
		url('https://www.alexdecarvalho.com.br/fr/wp-content/uploads/2014/09/clean-white-polygon-backgrounds-81.jpg');
}
</style>
<body>
<nav class="navbar navbar-expand-xl navbar-light bg-light">
		<a href="painel.jsp" class="navbar-brand"><i class="fa fa-user-md"></i>e-<b>Fisioterapia</b></a>
		<button type="button" class="navbar-toggler" data-toggle="collapse"
			data-target="#navbarCollapse">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="navbar-nav ml-auto">
			<div class="nav-item dropdown">
				<a href="#" data-toggle="dropdown"
					class="nav-link dropdown-toggle user-action"><img
					src="https://cdn-icons-png.flaticon.com/512/64/64572.png"
					width="25" height="40" class="avatar" alt="Avatar"> matheusgazzani <b class="caret"></b></a>
				<div class="dropdown-menu">
					<a href="index.jsp" class="dropdown-item"><i class="material-icons"></i>Sair</a></a>
				</div>
			</div>
		</div>
	</nav>
	<div class="container-lg">
		<div class="row">
			<div class="col-md-10 mx-auto">
				<div class="contact-form">
					<h1>Avalia????o</h1>
					<p class="hint-text">Prezado(a), preencha este formul??rio para
						registrar uma avalia????o.</p>
					<form action="inserirAvaliacao" method="post">
						<div class="row">
							<div class="col-sm-4">
								<div class="form-floating mb-3">
                    <select class="form-control" name="nome_paciente">
                    	<option>Pacientes</option>
						<%for (int i = 0; i < listapaciente.size(); i++) { %>
						<tr>
							<option><%=listapaciente.get(i).getNome()%></option>
						</tr>
					<%} %>
					</select>
                  </div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<div class="form-group row">
				<label class="col-form-label col-4">Data de avalia????o</label>
				<div class="col-8">
					<input type="date" class="form-control" name="dt_avaliacao">
				</div>
			</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-floating mb-3">
                    <select class="form-control" name="nome_fisioterapeuta">
                    	<option>Fisioterapeutas</option>
						<%for (int i = 0; i < lista.size(); i++) { %>
						<tr>
							<option><%=lista.get(i).getNome()%></option>
						</tr>
					<%} %>
					</select>
                  </div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputMessage">Ficha de Avalia????o</label>
							<textarea class="form-control" name="ficha_avaliacao" id="inputMessage" rows="5"
								required></textarea>
						</div>
						<button type="submit" class="btn btn-primary">Registrar</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<footer><center>Powered by Matheus Gazzani</center></footer>
</body>
</html>