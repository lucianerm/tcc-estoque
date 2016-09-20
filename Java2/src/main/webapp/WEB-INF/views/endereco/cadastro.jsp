<%@page import="com.luciianester.gestorestoque.entidades.Pessoa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
	String acao = "/GestorEstoque/pessoa/"+pessoa.getPessoaId()+"/endereco/gravar";
%>
<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">

  <%@include file="../pessoa/cabecalho.jsp" %>
  
  <div class="panel-body">
   
	 <jsp:include page="../pessoa/menu.jsp">
		<jsp:param name="endereco" value="active"/>
		<jsp:param name="pessoaId" value="${pessoa.pessoaId}"/>
	</jsp:include>
	
	<c:if test="${!empty mensagem}">
	
		<br/>
		<c:if test="${mensagemTipo == 'ERRO'}">
			<div class="alert alert-danger" role="alert">${mensagem }</div>
		</c:if>
		<c:if test="${mensagemTipo == 'SALVOU_SUCESSO'}">
			<div class="alert alert-success" role="alert">${mensagem }</div>
		</c:if>
		
	</c:if>

	<form:form action="<%= acao%>" commandName="objeto">
		
		<form:hidden path="enderecoId" />
		<br/>
		
		<label>CEP:</label>
		<br/>
		<form:input path="cep" data-mask="00000-000" />
		<br/>
		
		<label>Estado:</label>
		<br/>
		<form:input path="estado" size="50"/> 
		<br/>
		
		<label>Município:</label>
		<br/>
		<form:input path="municipio" size="50"/> 
		<br/>
		
		<label>Bairro:</label>
		<br/>
		<form:input path="bairro" size="50"/> 
		<br/>
		
		<label>Rua:</label>
		<br/>
		<form:input path="rua" size="50"/> 
		<br/>
		
		<label>Número:</label>
		<br/>
		<form:input path="numero" size="8"/> 
		<br/>
		<br/>
		
		<input type="submit" value="Salvar" class="btn btn-success" />
		<c:if test="${!empty objeto.enderecoId}">
			<td><a href="<c:url value='/pessoa/${pessoa.pessoaId}/endereco'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/pessoa/${pessoa.pessoaId}/endereco/${objeto.enderecoId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>
	
	<br/>
	<br/>
	
	<c:if test="${!empty lista}">
	
		<div class="panel panel-default">
		  <div class="panel-heading">enderecos</div>
		  <table  class="table">
			<tr>
				<th width="100">CEP</th>
				<th width="120">Estado</th>
				<th width="120">Município</th>
				<th width="150">Bairro</th>
				<th width="150">Rua</th>
				<th width="80">Número</th>
				<th width="200">Ações</th>
			</tr>
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.cep}</td>
					<td>${item.estado}</td>
					<td>${item.municipio}</td>
					<td>${item.bairro}</td>
					<td>${item.rua}</td>
					<td>${item.numero}</td>
					<td><a href="<c:url value='/pessoa/${pessoa.pessoaId}/endereco/${item.enderecoId}' />" class="btn btn-primary" >Editar</a> <a href="<c:url value='/pessoa/${pessoa.pessoaId}/endereco/excluir/${item.enderecoId}' />" class="btn btn-danger" >Excluir</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
	</c:if>
	
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<%@include file="../../base/bottom.jsp" %>