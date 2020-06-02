<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Pais, java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible"content="IE=edge">
		<meta name="viewport"content="width=device-width, initial-scale=1">
		<title>Lista de Paises</title>
		<link href="css/bootstrap.min.css"rel="stylesheet">
		<link href="css/style.css"rel="stylesheet">
	</head>
	<body>
	<h3>Lista de Paises</h3><br>
	<%
		ArrayList<Pais> paises = (ArrayList<Pais>)request.getAttribute("paises");
		for(Pais pais : paises)
		{
	%>
			<p>
				<label>ID:</label> <%=pais.getID() %><br>
				<label>Nome:</label> <%=pais.getNome() %><br>
				<label>População:</label> <%=pais.getPopulacao() %><br>
				<label>Area:</label> <%=pais.getArea() %>
			</p>
     <% } %>
     	</divid>
	</body>
</html>