<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	
	<%@include file="../../base/cabecalhopesquisa.jsp" %>

	<%@include file="../../base/pesquisatela.jsp" %>

	<table class="table">
	<tr>
		<th width="80">C�digo</th>
		<th width="200">Descri��o</th>
		<th width="200">Marca</th>
		<th width="200">Categoria</th>
	</tr>
	<c:if test="${!empty lista}">
		<c:forEach items="${lista}" var="objeto">
			<tr>
				<td>${objeto.produtoId}</td>
				<td>${objeto.descricao}</td>
				<td>${objeto.marca.descricao}</td>
				<td>${objeto.categoria.descricao}</td>
				<jsp:include page="../../base/barraacoes.jsp">
					<jsp:param name="caminho" value="produto"/>
					<jsp:param name="objetoId" value="${objeto.produtoId}"/>
				</jsp:include>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</div>
	
<%@include file="../../base/bottom.jsp" %>