<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Pessoa</title>
	
</head>
<body>
<h1>
	Pesquisa a Pessoa
</h1>

<div><a href="cadastro">Cadastrar</a></div>

<c:if test="${!empty lista}">
	<table class="tg">
	<tr>
		<th width="80">Código</th>
		<th width="120">Nome</th>
	</tr>
	<c:forEach items="${lista}" var="objeto">
		<tr>
			<td>${objeto.pessoaId}</td>
			<td>${objeto.nome}</td>
			<td><a href="<c:url value='/pessoa/${objeto.pessoaId}' />" >Edit</a></td>
			<td><a href="<c:url value='/pessoa/excluir/${objeto.pessoaId}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>