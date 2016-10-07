<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
  
	<div class="panel-heading">
		<ol class="breadcrumb">
			<li><a href="<c:url value='/usuario'/>">Voltar</a></li>
			<li class="active panel-title" >Cadastro Usuário</li>
		</ol>
	</div>
  
	<div class="panel-body">
	
		<%@include file="../../base/mensagem.jsp" %>
		
		<form:form action="gravar" commandName="objeto" autocomplete="off">
			
			<br/>
			<label>Código:</label>
			<br/>
			<form:input path="usuarioId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="usuarioId" />
			<br/>
			<br/>
			<label>Nome:</label>
			<br/>
			<form:input path="nome" />
			<br/>
			<br/>
			<label>Nome de Acesso:</label>
			<br/>
			<form:input path="nomeDeAcesso" />
			<br/>
			<br/>
			<label>Senha:</label>
			<br/>
			<form:input path="senha" />
			<br/>
			<br/>
			<label>Situação:</label>
			<br/>
			<select name="situacao" class="cmbSituacao js-example-basic-single js-states form-control">
				<option value="null" ${objeto.situacao == null ? 'selected' : ''}>Selecione uma Situação</option>
				<c:forEach items="${situacoes}" var="item">
					<option value="${item}" ${item == objeto.situacao ? 'selected' : ''}>${item.nome}</option>
				</c:forEach>
			</select>
			<br/>
			<br/>
			<label>Perfil:</label>
			<br/>
			<select name="perfil.perfilId" class="cmbPerfil js-example-basic-single js-states form-control">
				<option value="null" ${objeto.perfil.perfilId == null ? 'selected' : ''}>Selecione um Perfil</option>
				<c:forEach items="${listaPerfil}" var="item">
					<option value="${item.perfilId}" ${item.perfilId == objeto.perfil.perfilId ? 'selected' : ''}>${item.perfilId} - ${item.descricao}</option>
				</c:forEach>	
			</select>
			<br/>
			<br/>
			
			<jsp:include page="../../base/barrasalvar.jsp">
				<jsp:param name="caminho" value="usuario"/>
				<jsp:param name="objetoId" value="${objeto.usuarioId}"/>
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
		
		$(".cmbSituacao").select2({
			placeholder: "Selecione uma Situação"
		});
		
		$(".cmbPerfil").select2({
			placeholder: "Selecione um Perfil"
		});
		
	});

</script>