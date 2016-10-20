<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	
	<%@include file="../../base/cabecalhopesquisa.jsp" %>
	
	<%@include file="../../base/pesquisatela.jsp" %>

	<table class="table">
	<tr>
		<th width="80">Código</th>
		<th width="200">Descrição</th>
	</tr>
	<c:if test="${!empty lista}">
		<c:forEach items="${lista}" var="objeto">
			<tr>
				<td>${objeto.categoriaId}</td>
				<td>${objeto.descricao}</td>
				<jsp:include page="../../base/barraacoes.jsp">
					<jsp:param name="caminho" value="categoria"/>
					<jsp:param name="objetoId" value="${objeto.categoriaId}"/>
				</jsp:include>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</div>
	
<%@include file="../../base/bottom.jsp" %>