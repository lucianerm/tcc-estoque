<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	
	<%@include file="../../base/cabecalhopesquisa.jsp" %>

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