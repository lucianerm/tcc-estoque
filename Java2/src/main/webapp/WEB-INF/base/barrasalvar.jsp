<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<input type="submit" value="Salvar" class="btn btn-success" />
<c:if test="${!empty param.objetoId}">
	<td><a href="<c:url value='/${param.caminho}/cadastro'/>" class="btn btn-default" >Novo</a></td>
	<td><a href="<c:url value='/${param.caminho}/${param.objetoId}'/>" class="btn btn-default" >Cancelar</a></td>
</c:if>
