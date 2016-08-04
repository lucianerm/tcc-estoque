<%@page import="com.luciianester.gestorestoque.model.Categoria"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%

Categoria categoria = (Categoria) request.getAttribute("objeto");
	
%>
<html>
<head>
	<title>Cadastro da Categoria </title>
	
</head>
<body>
<div><a href="pesquisa">Voltar</a></div>
<h1>
	  Cadastro Categoria
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>


<form:form action="gravar" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="categoriaId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="categoriaId" />
	<br/>
	<label>Descrição:</label>
	<br/>
	<form:input path="descricao" />
	<br/>

	
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.categoriaId}">
		<td><a href="<c:url value='/categoria/cadastro'/>" >Novo</a></td>
		<td><a href="<c:url value='/categoria/${objeto.categoriaId}'/>" >Cancelar</a></td>
	</c:if>


</form:form>

</body>
</html>