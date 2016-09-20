<%@page import="com.luciianester.gestorestoque.entidades.EntradaItem"%>
<%@page import="com.luciianester.gestorestoque.entidades.Entrada"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	Entrada entrada = (Entrada) request.getAttribute("entrada");
	EntradaItem objeto = (EntradaItem) request.getAttribute("objeto");
	String acao = "/GestorEstoque/entrada/"+entrada.getEntradaId()+"/entradaitem/gravar";
	
%>
<%@include file="../../base/top.jsp" %>



<div><a href="<c:url value='/produto'/>">Voltar</a></div>
<h1>
	  Cadastro da Entrada 
</h1>

<ul class="nav nav-tabs">
  <li role="presentation"><a href="<c:url value='/entrada/${entrada.entradaId}'/>">Cadastro</a></li>
  <li role="presentation" class="active"><a>Produtos</a></li>
</ul>


<c:if test="${!empty mensagem}">

	<label>${mensagem } </label>
	
</c:if>


<form:form action="<%= acao%>" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="entradaItemId" readonly="true" size="8"  disabled="true" />
	<form:hidden path="entradaItemId" />
	<br/>
	<br/>
	<label>Produto:</label>
	<br/>
	<select name="produto.produtoId" onchange="onSelectedProduto(this);" class="cmbProduto js-example-basic-single js-states form-control">
		<option value="null" ${objeto.produto.produtoId == null ? 'selected' : ''}>Selecione um Produto</option>
		<c:forEach items="${listaProdutos}" var="item">
			<option value="${item.produtoId}" ${item.produtoId == objeto.produto.produtoId ? 'selected' : ''}>${item.produtoId} - ${item.descricao}</option>
		</c:forEach>	
	  <!-- option value="AL">Alabama</option>
	  <option value="WY">Wyoming</option-->
	</select>
	<br/>
	<br/>
	
	<label>Unidade de Medida:</label>
	<br/>
	<select id="cmbUnidadeDeMedida" name="unidadeDeMedida.unidadeDeMedidaId" class="cmbUnidadeDeMedida js-example-basic-single js-states form-control">
		<option value="null" ${objeto.unidadeDeMedida.unidadeDeMedidaId == null ? 'selected' : ''}>Selecione uma Unidade de Medida</option>
		<c:forEach items="${listaUnidadeDeMedida}" var="item">
			<option value="${item.unidadeDeMedidaId}" ${item.unidadeDeMedidaId == objeto.unidadeDeMedida.unidadeDeMedidaId ? 'selected' : ''}>${item.sigla} - ${item.descricao}</option>
		</c:forEach>	
	  <!-- option value="AL">Alabama</option>
	  <option value="WY">Wyoming</option-->
	</select>
	
	<br/>
	<br/>
	<label>Quantidade:</label>
	<br/>
	<form:input path="quantidade" /> 
	<br/>
	<br/>
	<label>Valor:</label>
	<br/>
	<form:input path="valor" /> 
	<br/>
	<br/>
	
	<input type="submit" value="Salvar" />
	<c:if test="${!empty objeto.entradaItemId}">
		<td><a href="<c:url value='/entrada/${entrada.entradaId}/entradaitem'/>" >Novo</a></td>
		<td><a href="<c:url value='/entrada/${entrada.entradaId}/entradaitem/${objeto.entradaItemId}'/>" >Cancelar</a></td>
	</c:if>
	
</form:form>

<br/>
<br/>

<c:if test="${!empty lista}">
	<table class="tg">
	<tr>
		<th width="80">Código</th>
		<th width="200">Produto</th>
		<th width="80">Quantidade</th>
		<th width="80">UM</th>
		<th width="100">Ações</th>
	</tr>
	<c:forEach items="${lista}" var="item">
		<tr>
			<td>${item.entradaItemId}</td>
			<td>${item.produto.descricao}</td>
			<td>${item.quantidade}</td>
			<td>${item.unidadeDeMedida.sigla}</td>
			<td><a href="<c:url value='/entrada/${entrada.entradaId}/entradaitem/${item.entradaItemId}' />" >Editar</a> <a href="<c:url value='/entrada/${entrada.entradaId}/entradaitem/excluir/${item.entradaItemId}' />" >Excluir</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<%@include file="../../base/bottom.jsp" %>



<link href="${pageContext.request.contextPath}/resources/css/select2.min.css" rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/resources/js/jquery.1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/select2.min.js"></script>


<script type="text/javascript">

	$(document).ready(function() {
		
		$(".cmbProduto").select2({
			placeholder: "Selecione um Produto"
		});
		
		$(".cmbUnidadeDeMedida").select2({
			placeholder: "Selecione uma Unidade de Medida"
		});
		
	});

	
	function onSelectedProduto(sel) {
		
		$.ajax({
            method : "get",
            dataType : "json",
            url : "/GestorEstoque/produto/"+sel.value+"/unidades",
            statusCode : {
                200 : function(data){
                    
                    var comboBox = document.getElementById("cmbUnidadeDeMedida");
                    
                    while (comboBox.options.length > 0) {                
                        comboBox.remove(0);
                    }
                    
                   	var option = document.createElement("option");
                   	option.value = null;
					option.text = "Selecione uma Unidade de Medida";
					option.selected = true;
					comboBox.add(option);
                      
                    for (var i = 0; i < data.lista.length; i++) {
                    	var option = document.createElement("option");
                    	option.value = data.lista[i].unidadeDeMedidaId;
                        option.text = data.lista[i].sigla + " - " + data.lista[i].descricao;
                        comboBox.add(option);
                    }
                    
                },
				404 : function(){
		            alert("Deu Erro");
		        }
            }
        });
	}

</script>
