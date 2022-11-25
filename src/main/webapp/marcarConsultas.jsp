<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
					<h1>Avaliação</h1>
					<p class="hint-text">Prezado(a), preencha este formulário para
						registrar uma avaliação.</p>
					<form action="inserirAvaliacao" method="post">
						<div class="row">
							<div class="col-sm-4">
								<div class="form-group">
									<label for="inputName">PACIENTE</label>
									<select
										class="form-control" id="inputName" name="nome_paciente">
										<option>Jaqueline Luiza Josefa Teixeira</option>
										<option>Julia Heloise Brenda da Luz</option>
										<option>Danilo Marcos Araújo</option>
									</select>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<div class="form-group row">
				<label class="col-form-label col-4">Data de avaliação</label>
				<div class="col-8">
					<input type="date" class="form-control" name="dt_avaliacao"
						required="true">
				</div>
			</div>
								</div>
							</div>
							<div class="col-sm-4">
								<div class="form-group">
									<label for="inputPhone">FISIOTERAPEUTA</label>
									<select
										class="form-control" id="inputName" name="nome_fisioterapeuta">
										<option>Antônio Vasconcelos</option>
										<option>Renan Levi Ramos</option>
										<option>Augusto Caleb Pereira</option>
									</select> 
								</div>
							</div>
						</div>
						<div class="form-group">
							<label for="inputMessage">Descrição do Paciente</label>
							<textarea class="form-control" name="descricao" id="inputMessage" rows="5"
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