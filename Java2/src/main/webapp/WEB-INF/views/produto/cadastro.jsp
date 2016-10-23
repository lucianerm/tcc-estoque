<%@page import="com.luciianester.gestorestoque.entidades.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	
	Produto produto = (Produto) request.getAttribute("objeto");
	
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
	  <li role="presentation" class="active"><a>Cadastro</a></li>
	  <c:if test="${empty objeto.produtoId}">
	  	<li class="disabled disabledTab" role="presentation"><a>Unidade de Medida</a></li>
	  </c:if>
	  <c:if test="${!empty objeto.produtoId}">
	  	<li role="presentation"><a href="<c:url value='/produto/${objeto.produtoId}/unidadedemedida'/>">Unidade de Medida</a></li>
	  </c:if>
	</ul>
	
	<%@include file="../../base/mensagem.jsp" %>
	
	<form:form action="gravar" commandName="objeto"  autocomplete="off">
		
		<br/>
		<label>Código:</label>
		<br/>
		<form:input path="produtoId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="produtoId" />
		<br/>
		<br/>
		<label>Descrição:</label>
		<br/>
		<form:input path="descricao" size="50"/>
		<br/>
		<br/>
		<label>Marca:</label>
		<br/>
		<select name="marca.marcaId" class="cmbMarca js-example-basic-single js-states form-control">
			<option value="null" ${objeto.marca.marcaId == null ? 'selected' : ''}>Selecione uma Marca</option>
			<c:forEach items="${listaMarcas}" var="item">
				<option value="${item.marcaId}" ${item.marcaId == objeto.marca.marcaId ? 'selected' : ''}>${item.marcaId} - ${item.descricao}</option>
			</c:forEach>	
		</select>
		<a href="<c:url value='/produto/cadastro'/>" class="btn btn-primary" >Nova Marca</a>
		<br/>
		<br/>
		<label>Categoria:</label>
		<br/>
		<select name="categoria.categoriaId" class="cmbCategoria js-example-basic-single js-states form-control">
			<option value="null" ${objeto.categoria.categoriaId == null ? 'selected' : ''}>Selecione uma Categoria</option>
			<c:forEach items="${listaCategorias}" var="item">
				<option value="${item.categoriaId}" ${item.categoriaId == objeto.categoria.categoriaId ? 'selected' : ''}>${item.categoriaId} - ${item.descricao}</option>
			</c:forEach>	
		</select>
		<a href="<c:url value='/produto/cadastro'/>" class="btn btn-primary" >Nova Categoria</a>
		<br/>
		<br/>
		
		<input type="submit" value="Salvar" class="btn btn-success" />
		<c:if test="${!empty objeto.produtoId}">
			<td><a href="<c:url value='/produto/cadastro'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/produto/${objeto.produtoId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>

  </div>
</div>

<%@include file="../../base/bottom.jsp" %>

<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".cmbMarca").select2({
			placeholder: "Selecione uma Marca"
		});
		
		$(".cmbCategoria").select2({
			placeholder: "Selecione uma Categoria"
		});
		
	});

</script>