<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">

	<li role="presentation" class="${param.entrada}"><a href="<c:url value='/entrada/${param.entradaId}'/>">Cadastro</a></li>
	<c:if test="${empty param.entradaId}">
		<li class="disabled disabledTab" role="presentation"><a>Produtos</a></li>
	</c:if>
	<c:if test="${!empty param.entradaId}">
		<li role="presentation" class="${param.item}"><a href="<c:url value='/entrada/${param.entradaId}/entradaitem'/>">Produtos</a></li>
	</c:if>
	
</ul>