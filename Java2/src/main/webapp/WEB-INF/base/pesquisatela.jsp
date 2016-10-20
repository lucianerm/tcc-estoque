<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<br/>
<label> </label>
<input id="campoPesquisa" value="${campoPesquisa}" />
<button onclick="pesquisar()" class="btn btn-primary">Pesquisar</button>
<button onclick="limpar()" class="btn btn-default">Limpar</button>
<input id="hiddenTela" type="hidden" VALUE="${telaAcessoCaminho}">
<br/>
<br/>

<script>
	function pesquisar() {
		var hiddenTela = document.getElementById("hiddenTela");
	    window.open("/GestorEstoque/"+hiddenTela.value+"?campoPesquisa="+document.getElementById("campoPesquisa").value,"_self")
	}
	function limpar() {
	    window.open("/GestorEstoque/"+hiddenTela.value,"_self")
	}
</script>