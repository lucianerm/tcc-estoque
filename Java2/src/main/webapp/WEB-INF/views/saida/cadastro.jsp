<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  
  <%@include file="cabecalho.jsp" %>
  
  <div class="panel-body">
   
	<jsp:include page="menu.jsp">
		<jsp:param name="saida" value="active"/>
		<jsp:param name="saidaId" value="${objeto.saidaId}"/>
	</jsp:include>
	
	<%@include file="../../base/mensagem.jsp" %>
	
	<form:form action="gravar" commandName="objeto" autocomplete="off">
		
		<br/>
		<label>Código:</label>
		<br/>
		<form:input path="saidaId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="saidaId" />
		<br/>
		<br/>
		<label>Data:</label>
		<br/>
		<form:input path="data" data-mask="00/00/0000 00:00:00" />
		<br/>
		<br/>
		<label>Cliente:</label>
		<br/>
		<select id="cmbCliente" name="cliente.pessoaId" class="cmbCliente js-example-basic-single js-states form-control">
			<option value="0" ${objeto.cliente.pessoaId == null ? 'selected' : ''}>Selecione um Cliente</option>
			<c:forEach items="${listaClientes}" var="item">
				<option value="${item.pessoaId}" ${item.pessoaId == objeto.cliente.pessoaId ? 'selected' : ''}>${item.pessoaId} - ${item.nome}</option>
			</c:forEach>	
		</select>
		<br/>
		<br/>
		
		<jsp:include page="../../base/barrasalvar.jsp">
			<jsp:param name="caminho" value="saida"/>
			<jsp:param name="objetoId" value="${objeto.saidaId}"/>
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
		
		$(".cmbCliente").select2({
			placeholder: "Selecione um Clente"
		});
		
	});

</script>