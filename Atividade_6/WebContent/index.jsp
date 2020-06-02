<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPEhtml>
<html lang="pt-br">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible"content="IE=edge">
		<meta name="viewport"content="width=device-width, initial-scale=1">
		<title>Paises</title>
		<link href="css/bootstrap.min.css"rel="stylesheet">
		<link href="css/style.css"rel="stylesheet">
	</head>
	<body>
		<!-- Barra superior comos menus denavegacao -->
		<c:import url="Menu.jsp"/>
		<!-- Container Principal -->
		<div id="main"class="container"> <br>
			<h3 class="page-header">Incluir Paises</h3>
			<!-- Formularioparainclusaodeclientes -->
			<form action="PaisController.do"method="post">
			<!-- area decampos do form -->
			<div class="row">
				<div class="form-group col-md-12">
					<label for="nome">Nome</label>
					<input type="text"class="formcontrol"name="nome"id="nome"maxlength="50"placeholder="Nome">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-6">
					<label for="fone">Populacaor</label>
					<input type="text"class="formcontrol"name="populacao"id="populacao"maxlength="30"placeholder="Populacao">
				</div>
				<div class="form-group col-md-6">
					<label for="email">Area</label>
					<input type="text"class="formcontrol"name="area"id="area"maxlength="60"placeholder="Area">
				</div>
			</div>
			<hr/>
			<div id="actions"class="row">
				<div class="col-md-12">
					<button type="submit"class="btn btnprimary"name="acao"value="Salvar">Salvar</button>
					<button type="submit"class="btn btnprimary"name="acao"value="Listar">Listar Paises</button>
					<a href="index.jsp"class="btn btn-default">Cancelar</a>
				</div>
			</div>
			</form>
		</div>
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>