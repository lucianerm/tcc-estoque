<%@page import="com.luciianester.gestorestoque.core.MensagemTipo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li><a href="<c:url value='/categoria'/>">Voltar</a></li>
			<li class="active panel-title" >Cadastro Categoria</li>
		</ol>
	</div>
  
	<div class="panel-body">
	
		<%@include file="../../base/mensagem.jsp" %>
		
		<form:form action="gravar" commandName="objeto" autocomplete="off">
			
			<br/>
			<label>Código:</label>
			<br/>
			<form:input path="categoriaId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="categoriaId" />
			<br/>
			<br/>
			<label>Descrição:</label>
			<br/>
			<form:input path="descricao" />
			<br/>
			<br/>
			
			<jsp:include page="../../base/barrasalvar.jsp">
				<jsp:param name="caminho" value="categoria"/>
				<jsp:param name="objetoId" value="${objeto.categoriaId}"/>
			</jsp:include>
			
		</form:form>

	</div>
</div>

<%@include file="../../base/bottom.jsp" %>