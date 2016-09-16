<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel-heading">
	<ol class="breadcrumb">
		<li class="active panel-title" >${telaAcessoNome}</li>
		<c:if test="${telaAcessoAlterar}">
			<li><a href="${telaAcessoCaminho}/cadastro" >Cadastrar</a></li>
		</c:if>
	</ol>
</div>