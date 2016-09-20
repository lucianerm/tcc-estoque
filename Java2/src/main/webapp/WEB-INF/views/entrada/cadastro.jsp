<%@page import="com.luciianester.gestorestoque.entidades.Entrada"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%

	Entrada entrada = (Entrada) request.getAttribute("objeto");
	
%>
<%@include file="../../base/top.jsp" %>
 
<div><a href="<c:url value='/entrada'/>">Voltar</a></div>
<h1>
	  Cadastro da Entrada
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>

<ul class="nav nav-tabs">
	<li role="presentation" class="active"><a>Cadastro</a></li>
	<c:if test="${empty objeto.entradaId}">
		<li class="disabled disabledTab" role="presentation"><a>Produtos</a></li>
	</c:if>
	<c:if test="${!empty objeto.entradaId}">
		<li role="presentation"><a href="<c:url value='/entrada/${objeto.entradaId}/entradaitem'/>">Produtos</a></li>
	</c:if>
</ul>

<form:form action="gravar" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="entradaId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="entradaId" />
	<br/>
	<label>Data:</label>
	<br/>
	<form:input path="data" />
	<br/>
	<br/>
	
	
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.entradaId}">
		<td><a href="<c:url value='/entrada/cadastro'/>" >Novo</a></td>
		<td><a href="<c:url value='/entrada/${objeto.entradaId}'/>" >Cancelar</a></td>
	</c:if>
	
</form:form>

<%@include file="../../base/bottom.jsp" %>