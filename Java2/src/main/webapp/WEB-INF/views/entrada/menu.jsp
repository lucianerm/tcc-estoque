<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="nav nav-tabs">

	<li role="presentation" class="${param.entrada}"><a href="<c:url value='/entrada/${param.entradaId}'/>">Cadastro</a></li>
	<c:if test="${empty param.entradaId}">
		<li class="disabled disabledTab" role="presentation"><a>Produtos</a></li>
	</c:if>
	<c:if test="${!empty param.entradaId}">
		<li role="presentation" class="${param.item}"><a href="<c:url value='/entrada/${param.entradaId}/entradaitem'/>">Produtos</a></li>
	</c:if>
	
	<li style="padding-left:30px; padding-top: 10px">
		Total: R$<fmt:formatNumber value="${totalEntrada}" minFractionDigits="2"/>
	</li>
	
</ul>


