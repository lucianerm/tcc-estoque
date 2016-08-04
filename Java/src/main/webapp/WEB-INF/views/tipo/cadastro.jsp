<%@page import="com.luciianester.gestorestoque.model.Tipo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%

Tipo tipo = (Tipo) request.getAttribute("objeto");
	
%>
<html>
<head>
	<title>Cadastro da Tipo </title>
	
</head>
<body>
<div><a href="pesquisa">Voltar</a></div>
<h1>
	  Cadastro Tipo
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>


<form:form action="gravar" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="tipoId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="tipoId" />
	<br/>
	<label>Tipo:</label>
	<br/>
	<form:input path="tipo" />
	<br/>
	<label>Quantidade:</label>
	<br/>
	<form:input path="quantidade" />
	<br/>
	
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.tipoId}">
		<td><a href="<c:url value='/tipo/cadastro'/>" >Novo</a></td>
		<td><a href="<c:url value='/tipo/${objeto.tipoId}'/>" >Cancelar</a></td>
	</c:if>


</form:form>

</body>
</html>