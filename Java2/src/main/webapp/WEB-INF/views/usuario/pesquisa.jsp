<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li class="active panel-title" >Usuário</li>
			<li><a href="usuario/cadastro" >Cadastrar</a></li>
		</ol>
	</div>

	<table class="table">
	<tr>
		<th width="80">Código</th>
		<th width="200">Nome</th>
	</tr>
	<c:if test="${!empty lista}">
		<c:forEach items="${lista}" var="objeto">
			<tr>
				<td>${objeto.usuarioId}</td>
				<td>${objeto.nome}</td>
				<jsp:include page="../../base/barraacoes.jsp">
					<jsp:param name="caminho" value="usuario"/>
					<jsp:param name="objetoId" value="${objeto.usuarioId}"/>
				</jsp:include>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</div>
	
<%@include file="../../base/bottom.jsp" %>