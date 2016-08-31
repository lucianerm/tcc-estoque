<%@page import="com.luciianester.gestorestoque.core.MensagemTipo"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  <div class="panel-heading">
	<ol class="breadcrumb">
	 
	  <li><a href="<c:url value='/pessoa'/>">Voltar</a></li>
	  <li class="active panel-title" >Cadastro Pessoa</li>
	 
	</ol>
  </div>
  
  <div class="panel-body">
   
	<ul class="nav nav-tabs">
	  <li role="presentation" class="active"><a>Cadastro</a></li>
	  <c:if test="${empty objeto.pessoaId}">
	  	<li class="disabled disabledTab" role="presentation"><a>Unidade de Medida</a></li>
	  </c:if>
	  <c:if test="${!empty objeto.pessoaId}">
	  	<li role="presentation"><a href="<c:url value='/pessoa/${objeto.pessoaId}/unidadedemedida'/>">Unidade de Medida</a></li>
	  </c:if>
	</ul>
	
	<%@include file="../../base/mensagem.jsp" %>
	
	<form:form action="gravar" commandName="objeto" autocomplete="off">
		
		<br/>
		<label>C�digo:</label>
		<br/>
		<form:input path="pessoaId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="pessoaId" />
		<br/>
		<br/>
		<label>Nome:</label>
		<br/>
		<form:input path="nome" size="50"/>
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
		<label>CPF/CNPJ:</label>
		<br/>
		<form:input path="cpfoucnpj" size="15" class="txtCpf" data-mask="000.000.000-00" data-mask-reverse="true"/>
		<br/>
		<br/>
		
		<jsp:include page="../../base/barrasalvar.jsp">
			<jsp:param name="caminho" value="pessoa"/>
			<jsp:param name="objetoId" value="${objeto.pessoaId}"/>
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