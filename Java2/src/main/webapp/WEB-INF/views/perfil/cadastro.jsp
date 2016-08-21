<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<%@include file="../../base/top.jsp" %>
 
<div><a href="<c:url value='/produto'/>">Voltar</a></div>
<h1>
	  Cadastro Perfil
</h1>

<c:if test="${!empty mensagem}">

	<label>${mensagem }</label>
	
</c:if>

<ul class="nav nav-tabs">
  <li role="presentation" class="active"><a>Cadastro</a></li>
  <c:if test="${empty objeto.perfilId}">
  	<li class="disabled disabledTab" role="presentation"><a>Unidade de Medida</a></li>
  </c:if>
  <c:if test="${!empty objeto.perfilId}">
  	<li role="presentation"><a href="<c:url value='/perfil/${objeto.perfilId}/unidadedemedida'/>">Unidade de Medida</a></li>
  </c:if>
</ul>

<form:form action="gravar" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="perfilId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="perfilId" />
	<br/>
	<br/>
	<label>Descrição:</label>
	<br/>
	<form:input path="descricao" />
	<br/>
	<br/>
	<label>Tipo:</label>
	<br/>
	<select name="tipo" class="cmbTipo js-example-basic-single js-states form-control">
		<option value="null" ${objeto.tipo == null ? 'selected' : ''}>Selecione um Tipo</option>
		<c:forEach items="${tipos}" var="item">
			<option value="${item}" ${item == objeto.tipo ? 'selected' : ''}>${item.nome}</option>
		</c:forEach>	
	</select>
	<br/>
	<br/>
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.perfilId}">
		<td><a href="<c:url value='/perfil/cadastro'/>" >Novo</a></td>
		<td><a href="<c:url value='/perfil/${objeto.perfilId}'/>" >Cancelar</a></td>
	</c:if>
	
</form:form>

<%@include file="../../base/bottom.jsp" %>

<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".cmbTipo").select2({
			placeholder: "Selecione um Tipo"
		});
		
	});

</script>
