<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	
	
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li class="active panel-title" >Pesquisa Saldos</li>
		</ol>
	</div>
  
	<form:form action="/GestorEstoque/saldos/pesquisar" commandName="objeto" autocomplete="off" >
	
		<div style="padding-left:10px; padding-right: 10px">
		
			<br/>
			<label>Data:</label>
			<form:input path="data" data-mask="00/00/0000" /> <form:checkbox path="filtraSaldoMinimo" /> Filtrar por Saldo Mínimo
			<br/>
			<br/>
			<input type="submit" value="Pesquisar" class="btn btn-success" />
		
		</div>
	
	</form:form>

	<br/>
	
	<c:if test="${!empty objeto.listaProdutos}">
	
		<div style="padding-left:10px; padding-right: 10px">
		
			<div class="panel panel-default">
			  <div class="panel-heading">Produtos</div>
			  <table  class="table">
				<tr>
					<th width="80">Código</th>
					<th width="150">Descrição</th>
					<th width="80">UM</th>
					<th width="80">Entradas</th>
					<th width="80">Saidas</th>
					<th width="80">Saldo Mínimo</th>
					<th width="80">Saldo</th>
				</tr>
				<c:forEach items="${objeto.listaProdutos}" var="item">
					<tr>
						<td>${item.produtoId}</td>
						<td>${item.descricao}</td>
						<td>${item.sigla}</td>
						<td>${item.entradas}</td>
						<td>${item.saidas}</td>
						<td>${item.saldoMinimo}</td>
						<td>${item.saldo}</td>
					</tr>
				</c:forEach>
				</table>
				
			</div>
			
		</div>
		
	</c:if>
	
</div>
	
<%@include file="../../base/bottom.jsp" %>

<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>