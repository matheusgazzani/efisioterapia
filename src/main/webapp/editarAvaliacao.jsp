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
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Avaliação | e-Fisioterapia</title>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/adicionarServico.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
<%@include file="css/addServico.css" %>
</style>
</head>
<html>
<body>
<div class="container-fluid ps-md-0">
    <div class="row g-0">
      <div class="d-none d-md-flex col-md-4 col-lg-6 bg-image"></div>
      <div class="col-md-8 col-lg-6">
        <div class="login d-flex align-items-center py-5">
          <div class="container">
            <div class="row">
              <div class="container-a">
  
                <form action="updateAvaliacao" method=post>
                <h3 class="login-heading mb-4">Editar avaliação</h3>
                <h6>Prezado(a), preencha corretamente todos os campos para editar uma avaliação.</h6>
                <div class="form-floating mb-3">
                    <input disabled class="form-control" name="cod_avaliacao" value="<%out.print(request.getAttribute("cod_avaliacao"));%>">
                    <label for="floatingInput">ID</label>
                  </div>
                  <div class="form-floating mb-3">
                    <input disabled class="form-control" name="nome_paciente" value="<%out.print(request.getAttribute("nome_paciente"));%>">
                    <label for="floatingInput">Paciente</label>
                  </div>
                  <div class="form-floating mb-3">
                    <select class="form-control" name="nome_paciente">
                    	<option>Paciente</option>
						<%for (int i = 0; i < listapaciente.size(); i++) { %>
						<tr>
							<option><%=listapaciente.get(i).getNome()%></option>
						</tr>
					<%} %>
					</select>
                  </div>
                  <div class="form-floating mb-3">
                    <input disabled class="form-control" name="nome_fisioterapeuta" value="<%out.print(request.getAttribute("nome_fisioterapeuta"));%>">
                    <label for="floatingInput">Fisioterapeuta</label>
                  </div>
                  <div class="form-floating mb-3">
                    <select class="form-control" name="nome_fisioterapeuta">
                    	<option>Fisioterapeuta</option>
						<%for (int i = 0; i < lista.size(); i++) { %>
						<tr>
							<option><%=lista.get(i).getNome()%></option>
						</tr>
					<%} %>
					</select>
                  </div>
                  <div class="form-floating mb-3">
                    <input rows="5" required class="form-control" name="ficha_avaliacao" value="<%out.print(request.getAttribute("ficha_avaliacao"));%>"></input>
                    <label for="floatingInput">Ficha de Avaliação</label>
                  </div>
                 <div class="form-floating mb-3">
                    <input type="date" class="form-control" name="dt_avaliacao" value="<%out.print(request.getAttribute("dt_avaliacao"));%>">
                    <label for="floatingInput">Data de avaliação</label>
                  </div>
                               
                                
                  
            <div class="d-grid">
                    <button class="btn btn-lg btn-primary btn-login text-uppercase fw-bold mb-2" type="submit">Salvar</button>
                  </div>
                  <div class="d-grid">
                    <a class="btn btn-danger" href="servicos" role="button">CANCELAR</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>  
</html>