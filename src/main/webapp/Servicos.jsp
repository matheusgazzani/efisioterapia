<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.efisioterapia.login.web.ServicosServlet"%>
<%@ page import="com.efisioterapia.login.database.ServicosDAO"%>
<%@ page import="com.efisioterapia.login.bean.ServicoBean"%>
<%@ page import="java.util.ArrayList"%>    
<%
ArrayList<ServicoBean> lista = (ArrayList<ServicoBean>) request.getAttribute("servicos");	
%>
<!DOCTYPE html>
<html lang="ptbr">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Serviços | e-Fisioterapia</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/cadastrarServicos.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	
	// Select/Deselect checkboxes
	var checkbox = $('table tbody input[type="checkbox"]');
	$("#selectAll").click(function(){
		if(this.checked){
			checkbox.each(function(){
				this.checked = true;                        
			});
		} else{
			checkbox.each(function(){
				this.checked = false;                        
			});
		} 
	});
	checkbox.click(function(){
		if(!this.checked){
			$("#selectAll").prop("checked", false);
		}
	});
});
<%@include file="js/confirmadorServico.js" %>	
</script>
</head>
<style>
<%@include file="css/cadastrarServicos.css" %> 
body {
	  background-image: url('https://www.alexdecarvalho.com.br/fr/wp-content/uploads/2014/09/clean-white-polygon-backgrounds-81.jpg');
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
<div class="container-xl">
	<div class="table-responsive">
		<div class="table-wrapper">
			<div class="table-title">
				<div class="row">
					<div class="col-sm-6">
						<h2>Serviços</b></h2>
					</div>
					<div class="col-sm-6">
						<a href="newservico" class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Adicionar Serviço</span></a>
					</div>
				</div>
			</div>
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>ID</th>
						<th>Serviço</th>
						<th>Valor R$</th>
                        <th>Descrição</th>
						<th>Profissional</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<%for (int i = 0; i < lista.size(); i++) { %>
						<tr>
							<td><%=lista.get(i).getCod_servico()%></td>
							<td><%=lista.get(i).getNome()%></td>
							<td><%=lista.get(i).getValor()%></td>
							<td><%=lista.get(i).getDescricao()%></td>
							<td><%=lista.get(i).getNome_fisioterapeuta()%></td>
							<td><a class="edit" href="selectServico?cod_servico=<%=lista.get(i).getCod_servico()%>"><i class="material-icons" data-toggle="tooltip">&#xE254;</i></a>
							<a class="delete" href="javascript:confirmarServico(<%=lista.get(i).getCod_servico()%>)"><i class="material-icons" data-toggle="tooltip">&#xE872;</i></a>
							</td>
						</tr>
					<%} %>
					</tbody>
			</table>
		</div>
	</div>        
</div>

<!-------------------------------------------------------------->
</body>
<footer><center>Powered by Matheus Gazzani</center></footer>
</html>