<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">

	<li role="presentation" class="${param.saida}"><a href="<c:url value='/saida/${param.saidaId}'/>">Cadastro</a></li>
	<c:if test="${empty param.saidaId}">
		<li class="disabled disabledTab" role="presentation"><a>Produtos</a></li>
	</c:if>
	<c:if test="${!empty param.saidaId}">
		<li role="presentation" class="${param.item}"><a href="<c:url value='/saida/${param.saidaId}/saidaitem'/>">Produtos</a></li>
	</c:if>
	
</ul>