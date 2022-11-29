<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.efisioterapia.login.database.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Profissional | e-Fisioterapia</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/painelSistema.css">
</head>
<style>
body {
	background-image:
		url('https://www.alexdecarvalho.com.br/fr/wp-content/uploads/2014/09/clean-white-polygon-backgrounds-81.jpg');
}
<%@include file ="css/painelSistema.css" %>
</style>
<body>
	<nav class="navbar navbar-expand-xl navbar-light bg-light">
		<a href="#" class="navbar-brand"><i class="fa fa-user-md"></i>e-<b>Fisioterapia</b></a>
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
	<div class="features-boxed">
		<div class="container">
			<div class="intro">
				<h2 class="text-center">Painel de Controle</h2>
			</div>
			<div class="row justify-content-center features">
				<div class="col-sm-6 col-md-5 col-lg-4 item">
					<div class="box">
						<img src="https://cdn-icons-png.flaticon.com/512/1823/1823830.png"
							width="80" height="110"></img>
						<h3 class="name">Pacientes</h3>
						<a href="paciente" type="button" class="btn btn-light">Acessar</a>
					</div>
				</div>

				<div class="col-sm-6 col-md-5 col-lg-4 item">
					<div class="box">
						<img src="https://cdn-icons-png.flaticon.com/512/3755/3755781.png"
							width="80" height="110"></img>
						<h3 class="name">Fisioterapeutas</h3>
						<a href="profissional" type="button" class="btn btn-light">Acessar</a>
					</div>
				</div>

				<div class="col-sm-6 col-md-5 col-lg-4 item">
					<div class="box">
						<img src="https://cdn-icons-png.flaticon.com/512/4112/4112981.png"
							width="77px" height="110"></img>
						<h3 class="name">Serviços</h3>
						<a type="button" href="servicos" class="btn btn-light">Acessar</a>
					</div>
				</div>

				<div class="col-sm-6 col-md-5 col-lg-4 item">
					<div class="box">
						<img src="https://cdn-icons-png.flaticon.com/512/6260/6260326.png"
							width="77px" height="110"></img>
						<h3 class="name">Avaliações</h3>
						<a type="button" href="avaliacoes" class="btn btn-light">Acessar</a>
					</div>
				</div>

				<div class="col-sm-6 col-md-5 col-lg-4 item">
					<div class="box">
						<img src="https://cdn-icons-png.flaticon.com/512/3208/3208998.png"
							width="77px" height="110"></img>
						<h3 class="name">Sessões</h3>
						<a type="button" href="avaliacaoservicos" class="btn btn-light">Acessar</a>
					</div>
				</div>

				<div class="col-sm-6 col-md-5 col-lg-4 item">
					<div class="box">
						<img src="https://cdn-icons-png.flaticon.com/512/5745/5745344.png"
							width="77px" height="110"></img>
						<h3 class="name">Relatórios</h3>
						<a type="button" href="Relatorios.jsp" class="btn btn-light">Acessar</a>
					</div>
				</div>

			</div>
		</div>
		<footer><center>Powered by Matheus Gazzani</center></footer>
	</div>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/js/bootstrap.bundle.min.js"></script>
</body>
</html>