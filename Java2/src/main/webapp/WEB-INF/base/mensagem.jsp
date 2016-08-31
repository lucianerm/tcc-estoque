<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${!empty mensagem}">

	<br/>
	<c:if test="${mensagemTipo == 'ERRO'}">
		<div class="alert alert-danger" role="alert">${mensagem }</div>
	</c:if>
	<c:if test="${mensagemTipo == 'SALVOU_SUCESSO'}">
		<div class="alert alert-success" role="alert">${mensagem }</div>
	</c:if>
	
</c:if>