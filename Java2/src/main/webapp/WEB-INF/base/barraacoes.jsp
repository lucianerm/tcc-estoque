<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<td>
	<c:if test="${telaAcessoAlterar}">
		<a href="<c:url value='/${param.caminho}/${param.objetoId}' />" class="btn btn-primary" >Editar</a>
	</c:if>
	<c:if test="${!telaAcessoAlterar}">
		<a href="<c:url value='/${param.caminho}/${param.objetoId}' />" class="btn btn-primary" >Visualizar</a>
	</c:if>
	<c:if test="${telaAcessoExcluir}">
		<a href="<c:url value='/${param.caminho}/excluir/${param.objetoId}' />" class="btn btn-danger" >Excluir</a>
	</c:if>
</td>