<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import = "model.Pais" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Pais</title>
	</head>
	
	<body>
		<%Pais pais = (Pais)request.getAttribute("pais"); %>
		ID: <%= pais.getID() %> <br>
		Nome: <%= pais.getNome()%> <br>
		População: <%= pais.getPopulacao() %> <br>
		Area: <%= pais.getArea() %> <br>
	</body>
</html>