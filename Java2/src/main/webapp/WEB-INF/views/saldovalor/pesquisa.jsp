<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	
	
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li class="active panel-title" >Pesquisa Saldos</li>
		</ol>
	</div>
  
	<form:form action="/GestorEstoque/saldovalor/pesquisar" commandName="objeto" autocomplete="off" >
	
		<div style="padding-left:10px; padding-right: 10px">
			
			<label>Produto:</label>
			<br/>
			<select name="produtoId" class="cmbProduto js-example-basic-single js-states form-control">
				<option value="0" ${objeto.produtoId == null ? 'selected' : ''}>Selecione um Produto</option>
				<c:forEach items="${listaProdutos}" var="item">
					<option value="${item.produtoId}" ${item.produtoId == objeto.produtoId ? 'selected' : ''}>${item.produtoId} - ${item.descricao}</option>
				</c:forEach>	
			</select>
			<br/>
			<br/>
			<input type="submit" value="Pesquisar" class="btn btn-success" />
		
		</div>
	
	</form:form>

	<br/>
	
	<c:if test="${!empty objeto.listaProdutos}">
	
		<div style="padding-left:10px; padding-right: 10px">
		
			<div class="panel panel-default">
			  <div class="panel-heading">Entradas</div>
			  <table  class="table">
				<tr>
					<th width="80">Data</th>
					<th width="80">UM</th>
					<th width="80">Entradas</th>
					<th width="80">Unitário</th>
					<th width="80">Total</th>
					<th width="80">Saidas</th>
					<th width="80">Unitário</th>
					<th width="80">Total</th>
					<th width="80">Ganho</th>
				</tr>
				<c:forEach items="${objeto.listaProdutos}" var="item">
					<tr>
						<td>${item.data}</td>
						<td>${item.sigla}</td>
						<td><fmt:formatNumber value="${item.entradas}" minFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${item.valorUnitario}" minFractionDigits="3"/></td>
						<td><fmt:formatNumber value="${item.valorTotal}" minFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${item.saidas}" minFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${item.saidasValorUnitario}" minFractionDigits="3"/></td>
						<td><fmt:formatNumber value="${item.saidasValorTotal}" minFractionDigits="2"/></td>
						<td><fmt:formatNumber value="${item.ganho}" minFractionDigits="2"/></td>
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

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".cmbProduto").select2({
			placeholder: "Selecione um Produto"
		});
		
	});

</script>