<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Gestor Estoque</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/shop-homepage.css" rel="stylesheet">

	<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
	<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>

	<style type="text/css">
		.loginmodal-container {
		  padding: 30px;
		  max-width: 350px;
		  width: 100% !important;
		  background-color: #F7F7F7;
		  margin: 0 auto;
		  border-radius: 2px;
		  box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
		  overflow: hidden;
		  font-family: roboto;
		}
		
		.loginmodal-container h1 {
		  text-align: center;
		  font-size: 1.8em;
		  font-family: roboto;
		}
		
		.loginmodal-container input[type=submit] {
		  width: 100%;
		  display: block;
		  margin-bottom: 10px;
		  position: relative;
		}
		
		.loginmodal-container input[type=text], input[type=password] {
		  height: 44px;
		  font-size: 16px;
		  width: 100%;
		  margin-bottom: 10px;
		  -webkit-appearance: none;
		  background: #fff;
		  border: 1px solid #d9d9d9;
		  border-top: 1px solid #c0c0c0;
		  /* border-radius: 2px; */
		  padding: 0 8px;
		  box-sizing: border-box;
		  -moz-box-sizing: border-box;
		}
		
		.loginmodal-container input[type=text]:hover, input[type=password]:hover {
		  border: 1px solid #b9b9b9;
		  border-top: 1px solid #a0a0a0;
		  -moz-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
		  -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
		  box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
		}
		
		.loginmodal {
		  text-align: center;
		  font-size: 14px;
		  font-family: 'Arial', sans-serif;
		  font-weight: 700;
		  height: 36px;
		  padding: 0 8px;
		/* border-radius: 3px; */
		/* -webkit-user-select: none;
		  user-select: none; */
		}
		
		.loginmodal-submit {
		  /* border: 1px solid #3079ed; */
		  border: 0px;
		  color: #fff;
		  text-shadow: 0 1px rgba(0,0,0,0.1); 
		  background-color: #4d90fe;
		  padding: 17px 0px;
		  font-family: roboto;
		  font-size: 14px;
		  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#4787ed)); */
		}
		
		.loginmodal-submit:hover {
		  /* border: 1px solid #2f5bb7; */
		  border: 0px;
		  text-shadow: 0 1px rgba(0,0,0,0.3);
		  background-color: #357ae8;
		  /* background-image: -webkit-gradient(linear, 0 0, 0 100%,   from(#4d90fe), to(#357ae8)); */
		}
		
		.loginmodal-container a {
		  text-decoration: none;
		  color: #666;
		  font-weight: 400;
		  text-align: center;
		  display: inline-block;
		  opacity: 0.6;
		  transition: opacity ease 0.5s;
		} 
		
		.login-help{
		  font-size: 12px;
		}
	
	</style>

</head>

<body>

	<div class="modal-dialog">
		<div class="loginmodal-container">
			<h1>Gestor Estoque</h1><br>
				
			<form:form action="/GestorEstoque/login/logar" commandName="objeto">
			
				<input type="text" name="nomeAcesso" placeholder="Usuário" autofocus>
				<input type="password" name="senha" placeholder="Senha">
				<input type="submit" class="login loginmodal-submit" value="Logar">
				
			</form:form>
		
		</div>
	</div>

</body>

</html>
