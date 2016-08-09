<%@page import="com.luciianester.gestorestoque.model.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%

	Produto produto = (Produto) request.getAttribute("objeto");
	
%>
<%@include file="../../base/top.jsp" %>
 
<div><a href="pesquisa">Voltar</a></div>
<h1>
	  Cadastro Produto
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>

<ul class="nav nav-tabs">
  <li role="presentation"><a href="<c:url value='/produto/${produto.produtoId}'/>">Cadastro</a></li>
  <li role="presentation" class="active"><a>Unidade de Medida</a></li>
</ul>


<%@include file="../../base/bottom.jsp" %>