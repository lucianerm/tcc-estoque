<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li><a href="<c:url value='/marca'/>">Voltar</a></li>
			<li class="active panel-title" >Cadastro Marca</li>
		</ol>
	</div>
  
	<div class="panel-body">
	
		<c:if test="${!empty mensagem}">
		
			<br/>
			<c:if test="${mensagemTipo == 'ERRO'}">
				<div class="alert alert-danger" role="alert">${mensagem }</div>
			</c:if>
			<c:if test="${mensagemTipo == 'SALVOU_SUCESSO'}">
				<div class="alert alert-success" role="alert">${mensagem }</div>
			</c:if>
			
		</c:if>
		
		<form:form action="gravar" commandName="objeto" autocomplete="off">
			
			<br/>
			<label>Código:</label>
			<br/>
			<form:input path="marcaId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="marcaId" />
			<br/>
			<br/>
			<label>Descrição:</label>
			<br/>
			<form:input path="descricao" />
			<br/>
			<br/>
			
			<c:if test="${telaAcessoAlterar}">
				<input type="submit" value="Salvar" class="btn btn-success" />
			</c:if>
			<c:if test="${!empty objeto.marcaId}">
				<c:if test="${telaAcessoAlterar}">
					<td><a href="<c:url value='/marca/cadastro'/>" class="btn btn-default" >Novo</a></td>
					<td><a href="<c:url value='/marca/${objeto.marcaId}'/>" class="btn btn-default" >Cancelar</a></td>
				</c:if>
			</c:if>
			
		</form:form>

	</div>
</div>

<%@include file="../../base/bottom.jsp" %>