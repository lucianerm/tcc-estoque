<%@page import="com.luciianester.gestorestoque.model.Pessoa"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	Pessoa pessoa = (Pessoa) request.getAttribute("pessoa");
	String acao = "/GestorEstoque/pessoa/"+pessoa.getPessoaId()+"/telefone/gravar";
%>
<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">

  <%@include file="../pessoa/cabecalho.jsp" %>
  
  <div class="panel-body">
   
	 <jsp:include page="../pessoa/menu.jsp">
		<jsp:param name="telefone" value="active"/>
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
		
		<form:hidden path="telefoneId" />
		<br/>
		<label>Descrição:</label>
		<br/>
		<form:input path="descricao" />
		<br/>
		<label>Número:</label>
		<br/>
		<form:input path="numero"  data-mask="00 0000-0000" data-mask-reverse="true"/> 
		<br/>
		<br/>
		
		<input type="submit" value="Salvar" class="btn btn-success" />
		<c:if test="${!empty objeto.telefoneId}">
			<td><a href="<c:url value='/pessoa/${pessoa.pessoaId}/telefone'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/pessoa/${pessoa.pessoaId}/telefone/${objeto.telefoneId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>
	
	<br/>
	<br/>
	
	<c:if test="${!empty lista}">
	
		<div class="panel panel-default">
		  <div class="panel-heading">Telefones</div>
		  <table  class="table">
			<tr>
				<th width="80">Descrição</th>
				<th width="80">Número</th>
				<th width="100">Ações</th>
			</tr>
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.descricao}</td>
					<td>${item.numero}</td>
					<td><a href="<c:url value='/pessoa/${pessoa.pessoaId}/telefone/${item.telefoneId}' />" class="btn btn-primary" >Editar</a> <a href="<c:url value='/pessoa/${pessoa.pessoaId}/telefone/excluir/${item.telefoneId}' />" class="btn btn-danger" >Excluir</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
	</c:if>
	
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<%@include file="../../base/bottom.jsp" %>