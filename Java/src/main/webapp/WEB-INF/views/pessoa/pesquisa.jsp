<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Pessoa</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
	
</head>
<body>
<h1>
	Pesquisa a Pessoa
</h1>

<div><a href="cadastro">Cadastrar</a></div>

<c:if test="${!empty lista}">
	<table class="tg">
	<tr>
		<th width="80">C�digo</th>
		<th width="200">Nome</th>
		<th width="80">A��es</th>
	</tr>
	<c:forEach items="${lista}" var="objeto">
		<tr>
			<td>${objeto.pessoaId}</td>
			<td>${objeto.nome}</td>
			<td><a href="<c:url value='/pessoa/${objeto.pessoaId}' />" >Editar</a> <a href="<c:url value='/pessoa/excluir/${objeto.pessoaId}' />" >Excluir</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>