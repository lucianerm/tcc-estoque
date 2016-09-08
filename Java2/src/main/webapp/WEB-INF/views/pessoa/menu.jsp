<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">

	<li role="presentation" class="${param.pessoa}"><a href="<c:url value='/pessoa/${param.pessoaId}'/>">Cadastro</a></li>
	<c:if test="${empty param.pessoaId}">
		<li class="disabled disabledTab" role="presentation"><a>Telefone</a></li>
		<li class="disabled disabledTab" role="presentation"><a>Endereço</a></li>
	</c:if>
	<c:if test="${!empty param.pessoaId}">
		<li role="presentation" class="${param.telefone}"><a href="<c:url value='/pessoa/${param.pessoaId}/telefone'/>">Telefone</a></li>
		<li role="presentation" class="${param.endereco}"><a href="<c:url value='/pessoa/${param.pessoaId}/endereco'/>">Endereço</a></li>
	</c:if>
	
</ul>