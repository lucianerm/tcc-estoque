<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs">

	<li role="presentation" class="${param.perfil}"><a href="<c:url value='/perfil/${param.perfilId}'/>">Cadastro</a></li>
	<c:if test="${empty param.perfilId}">
		<li class="disabled disabledTab" role="presentation"><a>Privilégio</a></li>
	</c:if>
	<c:if test="${!empty param.perfilId}">
	
		<c:if test="${param.tipo=='USUARIO'}">
			<li role="presentation" class="${param.item}"><a href="<c:url value='/perfil/${param.perfilId}/privilegios'/>">Privilégio</a></li>
		</c:if>
			
		<c:if test="${param.tipo=='ADMINISTRADOR'}">
			<li class="disabled disabledTab" role="presentation"><a>Privilégio</a></li>
		</c:if>
		
	</c:if>
	
</ul>