<%@page import="com.luciianester.gestorestoque.model.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%

	Produto produto = (Produto) request.getAttribute("objeto");
	
%>
<html>
<head>
	<title>Cadastro Produto </title>

	
	
</head>
<body>
<div><a href="pesquisa">Voltar</a></div>
<h1>
	  Cadastro Produto
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>

<form:form action="gravar" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="produtoId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="produtoId" />
	<br/>
	<label>Descrição:</label>
	<br/>
	<form:input path="descricao" />
	<br/>
	<br/>
	
	
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.produtoId}">
		<td><a href="<c:url value='/produto/cadastro'/>" >Novo</a></td>
		<td><a href="<c:url value='/produto/${objeto.produtoId}'/>" >Cancelar</a></td>
	</c:if>
	
</form:form>

</body>
</html>