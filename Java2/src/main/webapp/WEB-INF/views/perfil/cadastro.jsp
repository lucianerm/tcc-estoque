<%@page import="com.luciianester.gestorestoque.core.MensagemTipo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  
  <%@include file="cabecalho.jsp" %>
  
  <div class="panel-body">
   
	<jsp:include page="menu.jsp">
		<jsp:param name="perfil" value="active"/>
		<jsp:param name="perfilId" value="${objeto.perfilId}"/>
		<jsp:param name="tipo" value="${objeto.tipo}"/>
	</jsp:include>
	
	<%@include file="../../base/mensagem.jsp" %>
	
	<form:form action="gravar" commandName="objeto" autocomplete="off">
		
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
		
		<jsp:include page="../../base/barrasalvar.jsp">
			<jsp:param name="caminho" value="perfil"/>
			<jsp:param name="objetoId" value="${objeto.perfilId}"/>
		</jsp:include>
		
	</form:form>

  </div>
</div>

<%@include file="../../base/bottom.jsp" %>


<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		
		$(".cmbTipo").select2({
			placeholder: "Selecione um Tipo"
		});
		
	});

</script>