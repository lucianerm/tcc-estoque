<%@page import="com.luciianester.gestorestoque.entidades.UnidadeDeMedida"%>
<%@page import="com.luciianester.gestorestoque.entidades.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	Produto produto = (Produto) request.getAttribute("produto");
	UnidadeDeMedida objeto = (UnidadeDeMedida) request.getAttribute("objeto");
	String acao = "/GestorEstoque/produto/"+produto.getProdutoId()+"/unidadedemedida/gravar";
	//if (objeto.getUnidadeDeMedidaId()==null) {
	//	acao = "/produto/""/unidadedemedida/gravar";
	//}
%>
<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  <div class="panel-heading">
	<ol class="breadcrumb">
	 
	  <li><a href="<c:url value='/produto'/>">Voltar</a></li>
	  <li class="active panel-title" >Cadastro Produto</li>
	 
	</ol>
  </div>
  
  <div class="panel-body">
   
	<ul class="nav nav-tabs">
	  <li role="presentation"><a href="<c:url value='/produto/${produto.produtoId}'/>">Cadastro</a></li>
	  <li class="active"><a>Unidade de Medida</a></li>
	</ul>
	
	<%@include file="../../base/mensagem.jsp" %>

	<form:form action="<%= acao%>" commandName="objeto"  autocomplete="off">
		
		<br/>
		<label>Código:</label>
		<br/>
		<form:input path="unidadeDeMedidaId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="unidadeDeMedidaId" />
		<br/>
		<label>Sigla:</label>
		<br/>
		<form:input path="sigla" />
		<br/>
		<label>Descrição:</label>
		<br/>
		<form:input path="descricao" />
		<br/>
		<label>Quantidade:</label>
		<br/>
		<form:input path="quantidade" style="text-align:right;" /> Informar valor 1 caso for a medida padrão 
		<br/>
		<label>Valor de Venda:</label>
		<br/>
		<form:input path="valorDeVenda" data-mask="000000000.00" data-mask-reverse="true" style="text-align:right;"/>
		<br/>
		<br/>
		
		<input type="submit" value="Salvar" class="btn btn-success" />
		<c:if test="${!empty objeto.unidadeDeMedidaId}">
			<td><a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida/${objeto.unidadeDeMedidaId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>
	
	<br/>
	<br/>
	
	<c:if test="${!empty lista}">
	
		<div class="panel panel-default">
		  <div class="panel-heading">Todas as Unidades</div>
		  <table  class="table">
			<tr>
				<th width="60">Código</th>
				<th width="60">Sigla</th>
				<th width="200">Descrição</th>
				<th width="60">Quantidade</th>
				<th width="60">Valor</th>
				<th width="100">Ações</th>
			</tr>
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.unidadeDeMedidaId}</td>
					<td>${item.sigla}</td>
					<td>${item.descricao}</td>
					<td>${item.quantidade}</td>
					<td>${item.valorDeVenda}</td>
					<td><a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida/${item.unidadeDeMedidaId}' />" class="btn btn-primary" >Editar</a> <a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida/excluir/${item.unidadeDeMedidaId}' />" class="btn btn-danger" >Excluir</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
	</c:if>
	
  </div>
</div>

<%@include file="../../base/bottom.jsp" %>

<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>