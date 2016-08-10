<%@page import="com.luciianester.gestorestoque.model.UnidadeDeMedida"%>
<%@page import="com.luciianester.gestorestoque.model.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%
	Produto produto = (Produto) request.getAttribute("produto");
	UnidadeDeMedida objeto = (UnidadeDeMedida) request.getAttribute("objeto");
	String acao = "/GestorEstoque/produto/"+produto.getProdutoId()+"/unidadedemedida/gravar";
	//if (objeto.getUnidadeDeMedidaId()==null) {
	//	acao = "/produto/""/unidadedemedida/gravar";
	//}
%>
<%@include file="../../base/top.jsp" %>


 
<div><a href="<c:url value='/produto'/>">Voltar</a></div>
<h1>
	  Cadastro Produto 
</h1>

<ul class="nav nav-tabs">
  <li role="presentation"><a href="<c:url value='/produto/${produto.produtoId}'/>">Cadastro</a></li>
  <li role="presentation" class="active"><a>Unidade de Medida</a></li>
</ul>


<c:if test="${!empty mensagem}">

	<label>${mensagem } </label>
	
</c:if>


<form:form action="<%= acao%>" commandName="objeto">
	
	<br/>
	<label>C�digo:</label>
	<br/>
	<form:input path="unidadeDeMedidaId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="unidadeDeMedidaId" />
	<br/>
	<label>Sigla:</label>
	<br/>
	<form:input path="sigla" />
	<br/>
	<label>Descri��o:</label>
	<br/>
	<form:input path="descricao" />
	<br/>
	<label>Quantidade:</label>
	<br/>
	<form:input path="quantidade" /> Informar valor 1 caso for a medida padr�o 
	<br/>
	<br/>
	
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.unidadeDeMedidaId}">
		<td><a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida'/>" >Novo</a></td>
		<td><a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida/${objeto.unidadeDeMedidaId}'/>" >Cancelar</a></td>
	</c:if>
	
</form:form>

<br/>
<br/>

<c:if test="${!empty lista}">
	<table class="tg">
	<tr>
		<th width="80">C�digo</th>
		<th width="80">Sigla</th>
		<th width="200">Descri��o</th>
		<th width="80">Quantidade</th>
		<th width="100">A��es</th>
	</tr>
	<c:forEach items="${lista}" var="item">
		<tr>
			<td>${item.unidadeDeMedidaId}</td>
			<td>${item.sigla}</td>
			<td>${item.descricao}</td>
			<td>${item.quantidade}</td>
			<td><a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida/${item.unidadeDeMedidaId}' />" >Editar</a> <a href="<c:url value='/produto/${produto.produtoId}/unidadedemedida/excluir/${item.unidadeDeMedidaId}' />" >Excluir</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<%@include file="../../base/bottom.jsp" %>