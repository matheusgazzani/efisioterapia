<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.efisioterapia.login.web.AvaliacaoServicoServlet"%>
<%@ page import="com.efisioterapia.login.database.AvaliacaoServicoDAO"%>
<%@ page import="com.efisioterapia.login.bean.AvaliacaoServicoBean"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<AvaliacaoServicoBean> lista = (ArrayList<AvaliacaoServicoBean>) request.getAttribute("avaliacaoservicos");	
%>    
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Sessões | e-Fisioterapia</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	// Activate tooltips
	$('[data-toggle="tooltip"]').tooltip();
    
	// Filter table rows based on searched term
    $("#search").on("keyup", function() {
        var term = $(this).val().toLowerCase();
        $("table tbody tr").each(function(){
            $row = $(this);
            var name = $row.find("td:nth-child(2)").text().toLowerCase();
            console.log(name);
            if(name.search(term) < 0){                
                $row.hide();
            } else{
                $row.show();
            }
        });
    });
});
</script>
<style>
<%@include file="css/sessoes.css"%>	
</style>
</head>
<body>
    <div class="container-lg">
        <div class="table-responsive">
            <div class="table-wrapper">			
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2><b>Sessões</b></h2>
                        </div>
                        <div class="col-sm-6">
                            <div class="search-box">
                                <div class="input-group">								
                                    <input type="text" id="search" class="form-control" placeholder="Procurar por nome">
                                    <span class="input-group-addon"><i class="material-icons">&#xE8B6;</i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th style="width: 22%;">Fisioterapeuta</th>
                            <th style="width: 22%;">Paciente</th>
                            <th style="width: 22%;">Avaliação</th>
                            <th>Serviço</th>
                            <th>Quantidade</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
					<%for (int i = 0; i < lista.size(); i++) { %>
						<tr>
							<td><%=lista.get(i).getFisioterapeuta()%></td>
							<td><%=lista.get(i).getPaciente()%></td>
							<td><%=lista.get(i).getAvaliacao()%></td>
							<td><%=lista.get(i).getServico()%></td>
							<td><%=lista.get(i).getQuantidade()%></td>
							<td><a class="edit" href="#"><i class="material-icons" data-toggle="tooltip">&#xE254;</i></a>
							<a class="delete" href="#"><i class="material-icons" data-toggle="tooltip">&#xE872;</i></a>
							<a class="delete" href="#"><i class="material-icons" data-toggle="tooltip">&#xE871;</i></a>
							</td>
						</tr>
					<%} %>
					</tbody>
                </table>
            </div>
        </div>        
    </div>     
</body>
</html>