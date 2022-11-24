<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<title>Editar Profissional | e-Fisioterapia</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}css/exibirCliente.css"/>
<style>
<%@include file="css/cadastrarCliente.css" %>
body {
	  background-image: url('https://www.alexdecarvalho.com.br/fr/wp-content/uploads/2014/09/clean-white-polygon-backgrounds-81.jpg');
	}
</style>
</head>
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
	<div class="signup-form">
		<form action="updateProfissional">
			<h2>Editar Profissional</h2>
			<p>Prezado(a), forneça as informações corretas!</p>
			<hr>
			<div class="form-group">
				<div class="form-group">
					<label>ID</label><input readonly name="cod_fisioterapeuta" type="text"
						class="form-control"
						value="<%out.print(request.getAttribute("cod_fisioterapeuta"));%>">
				</div>
				<div class="form-group">
					<label>Nome</label><input name="nome" type="text"
						class="form-control"
						value="<%out.print(request.getAttribute("nome"));%>">
				</div>
				<div class="form-group">
					<label>Telefone</label> <input name="telefone"
						type="text" class="form-control"
						value="<%out.print(request.getAttribute("telefone"));%>">
				</div>
				<div class="form-group">
					<label>Data de Nascimento</label> <input name="dt_nascimento" type="date"
						class="form-control"
						value="<%out.print(request.getAttribute("dt_nascimento"));%>">
				</div>
				<div class="form-group">
					<label>E-mail</label> <input name="email"
						class="form-control"
						value="<%out.print(request.getAttribute("email"));%>"></input>
				</div>
				<div class="form-group">
					<label>Sexo</label> <input name="sexo" type="text"
						class="form-control"
						value="<%out.print(request.getAttribute("sexo"));%>">
				</div>
				<div class="form-group">
					<label>CREFITO</label> <input name="crefito" type="text"
						class="form-control"
						value="<%out.print(request.getAttribute("crefito"));%>">
				</div>
				<div class="form-group">
					<label>Especialidade</label> <input name="especialidade" type="text"
						class="form-control" value="<%out.print(request.getAttribute("especialidade"));%>"></input>
				</div>
				<div class="form-group">
					<label>CPF</label> <input name="cpf" type="text"
						class="form-control" value="<%out.print(request.getAttribute("cpf"));%>"></input>
				</div>
			</div>
			<div class="modal-footer">
				<a href="profissional" class="btn btn-info">Cancelar</a>
				<input type="submit" class="btn btn-info"
					value="Salvar">
			</div>
		</form>
	</div>
	<footer><center>Powered by Matheus Gazzani</center></footer>
</body>
</html>