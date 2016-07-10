<%@page import="com.luciianester.gestorestoque.model.Pessoa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%Pessoa pessoa = (Pessoa) request.getAttribute("objeto");%>
<html>
<head>
	<title>Cadastro Pessoa </title>

	
	
</head>
<body>
<div><a href="pesquisa">Voltar</a></div>
<h1>
	  Cadastro Pessoa
</h1>

<form:form action="gravar" commandName="objeto">

	<label>Código:</label>
	<br/>
	<form:input path="pessoaId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="pessoaId" />
	<br/>
	<label>Nome:</label>
	<br/>
	<form:input path="nome" />
	<br/>
	<br/>
	<input type="submit" value="Salvar" />

</form:form>

</body>
</html>