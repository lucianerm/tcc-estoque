<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<td>
	<a href="<c:url value='/${param.caminho}/${param.objetoId}' />" class="btn btn-primary" >Editar</a>
	<a href="<c:url value='/${param.caminho}/excluir/${param.objetoId}' />" class="btn btn-danger" >Excluir</a>
</td>