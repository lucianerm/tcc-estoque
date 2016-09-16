<%@page import="com.luciianester.gestorestoque.model.Perfil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	Perfil perfil = (Perfil) request.getAttribute("perfil");
	String acao = "/GestorEstoque/perfil/"+perfil.getPerfilId()+"/privilegio/gravar";
%>
<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">

  <%@include file="../perfil/cabecalho.jsp" %>
  
  <div class="panel-body">
   
	<jsp:include page="../perfil/menu.jsp">
		<jsp:param name="item" value="active"/>
		<jsp:param name="perfilId" value="${perfil.perfilId}"/>
		<jsp:param name="tipo" value="${perfil.tipo}"/>
	</jsp:include>
	
	<%@include file="../../base/mensagem.jsp" %>

	<form:form action="<%= acao%>" commandName="objeto">
		
		<form:hidden path="privilegioId" />
		<br/>
		<label>Tela:</label>
		<br/>
		<select name="tela" class="cmbTela js-example-basic-single js-states form-control">
			<option value="null" ${objeto.tela == null ? 'selected' : ''}>Selecione uma Tela</option>
			<c:forEach items="${telas}" var="item">
				<option value="${item}" ${item == objeto.tela ? 'selected' : ''}>${item.nome}</option>
			</c:forEach>
		</select>
		<br/>
		<br/>
		<form:checkbox path="alterar" /> Alterar 
		<form:checkbox path="excluir" /> Excluir 
		<br/>
		<br/>
		
		<input type="submit" value="Salvar" class="btn btn-success" />
		<c:if test="${!empty objeto.privilegioId}">
			<td><a href="<c:url value='/perfil/${perfil.perfilId}/privilegio'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/perfil/${perfil.perfilId}/privilegio/${objeto.privilegioId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>
	
	<br/>
	<br/>
	
	<c:if test="${!empty lista}">
	
		<div class="panel panel-default">
		  <div class="panel-heading">privilegios</div>
		  <table  class="table">
			<tr>
				<th width="100">Nome</th>
				<th width="80">Alterar</th>
				<th width="80">Excluir</th>
			</tr>
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.tela.nome}</td>
					<td>${item.alterar}</td>
					<td>${item.excluir}</td>
					<td><a href="<c:url value='/perfil/${perfil.perfilId}/privilegio/${item.privilegioId}' />" class="btn btn-primary" >Editar</a> <a href="<c:url value='/perfil/${perfil.perfilId}/privilegio/excluir/${item.privilegioId}' />" class="btn btn-danger" >Excluir</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
	</c:if>
	
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<%@include file="../../base/bottom.jsp" %>

<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".cmbTela").select2({
			placeholder: "Selecione uma Tela"
		});
		
		
	});

</script>