<%@page import="com.luciianester.gestorestoque.core.MensagemTipo"%>
<%@page import="com.luciianester.gestorestoque.model.Produto"%>
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
	
	<c:if test="${!empty mensagem}">
	
		<br/>
		<c:if test="${mensagemTipo == 'ERRO'}">
			<div class="alert alert-danger" role="alert">${mensagem }</div>
		</c:if>
		<c:if test="${mensagemTipo == 'SALVOU_SUCESSO'}">
			<div class="alert alert-success" role="alert">${mensagem }</div>
		</c:if>
		
	</c:if>
	
	<form:form action="gravar" commandName="objeto">
		
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
		
		
		<input type="submit" value="Salvar" class="btn btn-primary" />
		<c:if test="${!empty objeto.produtoId}">
			<td><a href="<c:url value='/produto/cadastro'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/produto/${objeto.produtoId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>

  </div>
</div>

<%@include file="../../base/bottom.jsp" %>