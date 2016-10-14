<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">
	
	<%@include file="../../base/cabecalhopesquisa.jsp" %>
	
	<br/>
	
	<label> </label>
	<input id="campoPesquisa" value="${campoPesquisa}" />
	<button onclick="pesquisar()" class="btn btn-primary">Pesquisar</button>
	<button onclick="limpar()" class="btn btn-primary">Limpar</button>

	<br/>
	<br/>
	
	<table class="table">
	<tr>
		<th width="80">Código</th>
		<th width="200">Descrição</th>
	</tr>
	<c:if test="${!empty lista}">
		<c:forEach items="${lista}" var="objeto">
			<tr>
				<td>${objeto.marcaId}</td>
				<td>${objeto.descricao}</td>
				<jsp:include page="../../base/barraacoes.jsp">
					<jsp:param name="caminho" value="marca"/>
					<jsp:param name="objetoId" value="${objeto.marcaId}"/>
				</jsp:include>
			</tr>
		</c:forEach>
	</c:if>
	</table>
</div>
	
<%@include file="../../base/bottom.jsp" %>

<script>
	function pesquisar() {
	    window.open("/GestorEstoque/marca?campoPesquisa="+document.getElementById("campoPesquisa").value,"_self")
	}
	function limpar() {
	    window.open("/GestorEstoque/marca","_self")
	}
</script>