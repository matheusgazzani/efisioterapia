<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<title>Profissional | e-Fisioterapia</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<style>
<%@
include file ="css/cadastrarCliente.css" %> 
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
	<div class="signup-form">
		<form action="insertProfissional" method="post" class="form-horizontal">
			<div class="row">
				<div class="col-8 offset-4">
					<h2>Registro de Profissional</h2>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-form-label col-4">Nome</label>
				<div class="col-8">
					<input type="text" class="form-control" name="nome" required="true">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-form-label col-4">Telefone</label>
				<div class="col-8">
					<input type="phone" class="form-control" name="telefone"
						required="true">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-form-label col-4">Data de Nascimento</label>
				<div class="col-8">
					<input type="date" class="form-control" name="dt_nascimento"
						required="true">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-form-label col-4">E-mail</label>
				<div class="col-8">
					<input type="email" class="form-control" name="email"
						required="true">
				</div>
			</div>

			<div class="form-group row">
				<label for="sel1" class="col-form-label col-4">Sexo</label>
				<div class="col-8">
					<select class="form-control" id="sel1" name="sexo">
						<option>M</option>
						<option>F</option>
					</select>
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-form-label col-4">CREFITO</label>

				<div class="col-8">
					<input type="text" class="form-control" name="crefito"
						required="true">
				</div>
			</div>

			<div class="form-group row">
				<label class="col-form-label col-4">Especialidade</label>
				<div class="col-8">
					<input type="text" class="form-control" name="especialidade"
						required="true">
				</div>
			</div>
			
			<div class="form-group row">
				<label class="col-form-label col-4">CPF</label>
				<div class="col-8">
					<input type="text" class="form-control" name="cpf"
						required="true">
				</div>
			</div>

			<div class="form-group row">
				<div class="col-8 offset-4">
					<button type="submit" class="btn btn-primary btn-lg">Cadastrar</button>
					<a class="btn btn-info" href="profissional" role="button">Cancelar</a>
				</div>
			</div>
		</form>
	</div>
	<footer><center>Powered by Matheus Gazzani</center></footer>
</body>
</html>