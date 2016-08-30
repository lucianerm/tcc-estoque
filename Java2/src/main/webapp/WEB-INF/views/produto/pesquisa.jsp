<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<%@include file="../../base/top.jsp" %>

<c:if test="${!empty lista}">
	
	<div class="panel panel-default">
	  <div class="panel-heading">
		<ol class="breadcrumb">
		 
		  <li class="active panel-title" >Produtos</li>
		  <li><a href="produto/cadastro">Cadastrar</a></li>
		 
		</ol>
	  </div>
  
	  <c:if test="${!empty lista}">
			<table class="table">
			<tr>
				<th width="80">Código</th>
				<th width="200">Descrição</th>
				<th width="80">Ações</th>
			</tr>
			<c:forEach items="${lista}" var="objeto">
				<tr>
					<td>${objeto.produtoId}</td>
					<td>${objeto.descricao}</td>
					<td><a href="<c:url value='/produto/${objeto.produtoId}' />" class="btn btn-primary" >Editar</a> <a href="<c:url value='/produto/excluir/${objeto.produtoId}' />" class="btn btn-danger" >Excluir</a></td>
				</tr>
			</c:forEach>
			</table>
		</c:if>
	</div>
	
</c:if>

<%@include file="../../base/bottom.jsp" %>