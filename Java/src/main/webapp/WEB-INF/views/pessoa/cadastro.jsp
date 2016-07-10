<%@page import="com.luciianester.gestorestoque.model.Pessoa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%

	Pessoa pessoa = (Pessoa) request.getAttribute("objeto");
	
%>
<html>
<head>
	<title>Cadastro Pessoa </title>

	
	
</head>
<body>
<div><a href="pesquisa">Voltar</a></div>
<h1>
	  Cadastro Pessoa
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>

<form:form action="gravar" commandName="objeto">
	
	<br/>
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
	<td><a href="<c:url value='/pessoa/${objeto.pessoaId}'/>" >Cancelar</a></td>

</form:form>

</body>
</html>