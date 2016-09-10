<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li class="active panel-title" >Saidas</li>
			<li><a href="saida/cadastro" >Cadastrar</a></li>
		</ol>
	</div>

	<table class="table">
	<tr>
		<th width="80">C�digo</th>
		<th width="200">Data</th>
	</tr>
	<c:if test="${!empty lista}">
		<c:forEach items="${lista}" var="objeto">
			<tr>
				<td>${objeto.saidaId}</td>
				<td>${objeto.data}</td>
				<jsp:include page="../../base/barraacoes.jsp">
					<jsp:param name="caminho" value="saida"/>
					<jsp:param name="objetoId" value="${objeto.saidaId}"/>
				</jsp:include>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</div>
	
<%@include file="../../base/bottom.jsp" %>