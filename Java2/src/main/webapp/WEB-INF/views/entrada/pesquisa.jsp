<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">

	<%@include file="../../base/cabecalhopesquisa.jsp" %>

	<table class="table">
	<tr>
		<th width="80">Código</th>
		<th width="80">Fornecedor</th>
		<th width="80">Data</th>
		<th width="80">Ações</th>
	</tr>
	<c:if test="${!empty lista}">
		<c:forEach items="${lista}" var="objeto">
			<tr>
				<td>${objeto.entradaId}</td>
				<td>${objeto.fornecedor.nome}</td>
				<td data-mask="0000/00/00 00:00:00">${objeto.data}</td>
				<jsp:include page="../../base/barraacoes.jsp">
					<jsp:param name="caminho" value="entrada"/>
					<jsp:param name="objetoId" value="${objeto.entradaId}"/>
				</jsp:include>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<%@include file="../../base/bottom.jsp" %>