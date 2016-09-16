<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${telaAcessoAlterar}">
	<input type="submit" value="Salvar" class="btn btn-success" />
</c:if>
<c:if test="${!empty param.objetoId}">
	<c:if test="${telaAcessoAlterar}">
		<td><a href="<c:url value='/${param.caminho}/cadastro'/>" class="btn btn-default" >Novo</a></td>
		<td><a href="<c:url value='/${param.caminho}/${param.objetoId}'/>" class="btn btn-default" >Cancelar</a></td>
	</c:if>
</c:if>
