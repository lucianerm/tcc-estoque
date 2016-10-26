<%@page import="com.luciianester.gestorestoque.entidades.Entrada"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	
	Entrada entrada = (Entrada) request.getAttribute("objeto");
	String acaoNovoFornecedor = "/entrada/novofornecedor/"+entrada.getEntradaId();
	if (entrada.getEntradaId()==null) {
		acaoNovoFornecedor = "/entrada/novofornecedor/0";
	}
	
%>
<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  
  <%@include file="cabecalho.jsp" %>
  
  <div class="panel-body">
   
	<jsp:include page="menu.jsp">
		<jsp:param name="entrada" value="active"/>
		<jsp:param name="entradaId" value="${objeto.entradaId}"/>
	</jsp:include>
	
	<%@include file="../../base/mensagem.jsp" %>
	
	<form:form action="gravar" commandName="objeto" autocomplete="off">
			
		<br/>
		<label>Código:</label>
		<br/>
		<form:input path="entradaId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="entradaId" />
		<br/>
		<br/>
		<label>Data:</label>
		<br/>
		<form:input path="data" data-mask="00/00/0000 00:00:00" />
		<br/>
		<br/>
		<label>Número:</label>
		<br/>
		<form:input path="numero" />
		<br/>
		<br/>
		<label>Fornecedor:</label>
		<br/>
		<select id="cmbFornecedor" name="fornecedor.pessoaId" class="cmbFornecedor js-example-basic-single js-states form-control">
			<option value="0" ${objeto.fornecedor.pessoaId == null ? 'selected' : ''}>Nenhum Fornecedor</option>
			<c:forEach items="${listaFornecedores}" var="item">
				<option value="${item.pessoaId}" ${item.pessoaId == objeto.fornecedor.pessoaId ? 'selected' : ''}>${item.pessoaId} - ${item.nome}</option>
			</c:forEach>	
		</select>
		<a href="<c:url value='<%= acaoNovoFornecedor%>'/>" class="btn btn-primary" >Novo Fornecedor</a>
		<br/>
		<br/>
		
		<jsp:include page="../../base/barrasalvar.jsp">
			<jsp:param name="caminho" value="entrada"/>
			<jsp:param name="objetoId" value="${objeto.entradaId}"/>
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
		
		$(".cmbFornecedor").select2({
			placeholder: "Nenhum Fornecedor"
		});
		
	});

</script>