<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="com.efisioterapia.login.web.PacienteServlet"%>
<%@ page import="com.efisioterapia.login.database.PacienteDAO"%>
<%@ page import="com.efisioterapia.login.bean.PacienteBean"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<PacienteBean> lista = (ArrayList<PacienteBean>) request.getAttribute("pacientes");	
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Pacientes | e-Fisioterapia</title>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">	
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/exibirCliente.css"/>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</style>
<script>
<%@include file="js/confirmador.js" %>	
	$(document).ready(function() {
		// Activate tooltip
		$('[data-toggle="tooltip"]').tooltip();

		// Select/Deselect checkboxes
		var checkbox = $('table tbody input[type="checkbox"]');
		$("#selectAll").click(function() {
			if (this.checked) {
				checkbox.each(function() {
					this.checked = true;
				});
			} else {
				checkbox.each(function() {
					this.checked = false;
				});
			}
		});
		checkbox.click(function() {
			if (!this.checked) {
				$("#selectAll").prop("checked", false);
			}
		});
	});
</script>
</head>
<style>
body {
	background-image:
		url('https://www.alexdecarvalho.com.br/fr/wp-content/uploads/2014/09/clean-white-polygon-backgrounds-81.jpg');
}
<%@include file="css/exibirClientes.css" %>
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
	<div class="container-xl">
		<div class="table-responsive">
			<div class="table-wrapper">
				<div class="table-title">
					<div class="row">
						<div class="col-sm-6">
							<h2><b>Pacientes</b></h2>
						</div>
						<div class="col-sm-6">
						<a href="cadastrarPaciente.jsp" class="btn btn-success"><i class="material-icons">&#xE147;</i><span>Adicionar paciente</span></a>
						</div>
					</div>
				</div>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<!-- ATRIBUTOS -->
							<th>ID</th>	
							<th>Nome</th>
							<th>Data de Nascimento</th>
							<th>E-mail</th>
							<th>Profissão</th>
							<th>Sexo</th>
							<th>CPF</th>
							<th>Telefone</th>
							<th>Ações</th>
						</tr>
					</thead>
					<tbody>
					<%for (int i = 0; i < lista.size(); i++) { %>
						<tr>
							<td><%=lista.get(i).getCod_paciente()%></td>
							<td><%=lista.get(i).getNome()%></td>
							<td><%=lista.get(i).getDt_nascimento()%></td>
							<td><%=lista.get(i).getEmail()%></td>
							<td><%=lista.get(i).getProfissao()%></td>
							<td><%=lista.get(i).getSexo()%></td>
							<td><%=lista.get(i).getCpf()%></td>
							<td><%=lista.get(i).getTelefone() %></td>
							<td><a class="edit" href="selectPaciente?cod_paciente=<%=lista.get(i).getCod_paciente()%>"><i class="material-icons" data-toggle="tooltip">&#xE254;</i></a>
							<a class="delete" href="javascript:confirmar(<%=lista.get(i).getCod_paciente()%>)"><i class="material-icons" data-toggle="tooltip">&#xE872;</i></a>
							</td>
						</tr>
					<%} %>
					</tbody>
				</table>

			</div>
		</div>
	</div>
	<!-- FIM DO MODAL DELETAR -->
	<footer><center>Powered by Matheus Gazzani</center></footer>
</body>
</html>