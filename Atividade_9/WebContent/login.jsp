<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPEhtml>
<html lang="pt-br">
	<head>
    	<meta charset="utf-8">
    	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    	<meta name="viewport" content="width=device-width, initial-scale=1">
    	<title>Login - Paises</title>
    	<link href="css/bootstrap.min.css"rel="stylesheet">
    	<link href="css/style.css"rel="stylesheet">
	</head>
	<body>
		<div id="main" class="container">
			<h3 class="page-header">Login</h3>
    		<form action="UsuarioController.do" method="post">
    			<div class="row">
                		<div class="form-group col-md-12">
                    		<label for="login">Login</label>
                    		<input type="text" class="form-control" name="login" id="login" required maxlength="100" placeholder="Login">
                		</div>
            	</div>
            	<div class="row">
                		<div class="form-group col-md-12">
                    		<label for="senha">Senha</label>
                    		<input type="password" class="form-control" name="senha" id="senha" required maxlength="100" placeholder="Senha">
                		</div>
            	</div>
            	<div class="col-md-12">
            		<button type="submit" class="btn btn-primary" name="acao" value="Login">Login</button>	
           	</div>
    		</form>
    	</div>
    	<script src="js/jquery.min.js"></script>
    	<script src="js/bootstrap.min.js"></script>
	</body>
</html>