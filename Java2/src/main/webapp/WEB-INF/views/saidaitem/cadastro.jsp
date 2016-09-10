<%@page import="com.luciianester.gestorestoque.model.Saida"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
	Saida saida = (Saida) request.getAttribute("saida");
	String acao = "/GestorEstoque/saida/"+saida.getSaidaId()+"/saidaitem/gravar";
%>
<%@include file="../../base/top.jsp" %>

<div class="panel panel-default">

  <%@include file="../saida/cabecalho.jsp" %>
  
  <div class="panel-body">
   
	 <jsp:include page="../saida/menu.jsp">
		<jsp:param name="item" value="active"/>
		<jsp:param name="saidaId" value="${saida.saidaId}"/>
	</jsp:include>
	
	<c:if test="${!empty mensagem}">
	
		<br/>
		<c:if test="${mensagemTipo == 'ERRO'}">
			<div class="alert alert-danger" role="alert">${mensagem }</div>
		</c:if>
		<c:if test="${mensagemTipo == 'SALVOU_SUCESSO'}">
			<div class="alert alert-success" role="alert">${mensagem }</div>
		</c:if>
		
	</c:if>

	<form:form action="<%= acao%>" commandName="objeto">
	
		<br/>
		<form:input path="saidaItemId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="saidaItemId" />
		<br/>
		<br/>
		<label>Produto:</label>
		<br/>
		<select name="entradaItem.entradaItemId" onchange="onSelectedProduto(this);" class="cmbProduto js-example-basic-single js-states form-control">
			<option value="null" ${objeto.entradaItem.entradaItemId == null ? 'selected' : ''}>Selecione um Produto</option>
			<c:forEach items="${listProdutos}" var="item">
				<option value="${item.entradaItemId}" ${item.entradaItemId == objeto.entradaItem.entradaItemId ? 'selected' : ''}>${item.entradaItemId} - ${item.produto.produtoId} - ${item.produto.descricao}</option>
			</c:forEach>	
		</select>
		<br/>
		<br/>
		
		<label>Unidade de Medida:</label>
		<br/>
		<select id="cmbUnidadeDeMedida" name="unidadeDeMedida.unidadeDeMedidaId" class="cmbUnidadeDeMedida js-example-basic-single js-states form-control">
			<option value="null" ${objeto.unidadeDeMedida.unidadeDeMedidaId == null ? 'selected' : ''}>Selecione uma Unidade de Medida</option>
			<c:forEach items="${listUnidadeDeMedida}" var="item">
				<option value="${item.unidadeDeMedidaId}" ${item.unidadeDeMedidaId == objeto.unidadeDeMedida.unidadeDeMedidaId ? 'selected' : ''}>${item.sigla} - ${item.descricao}</option>
			</c:forEach>	
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
		
		<input type="submit" value="Salvar" class="btn btn-success" />
		<c:if test="${!empty objeto.saidaItemId}">
			<td><a href="<c:url value='/saida/${saida.saidaId}/saidaitem'/>" class="btn btn-default" >Novo</a></td>
			<td><a href="<c:url value='/saida/${saida.saidaId}/saidaitem/${objeto.saidaItemId}'/>" class="btn btn-default" >Cancelar</a></td>
		</c:if>
		
	</form:form>
	
	<br/>
	<br/>
	
	<c:if test="${!empty lista}">
	
		<div class="panel panel-default">
		  <div class="panel-heading">Produtos</div>
		  <table  class="table">
			<tr>
				<th width="80">C�digo</th>
				<th width="200">Produto</th>
				<th width="80">Quantidade</th>
				<th width="80">UM</th>
				<th width="100">A��es</th>
			</tr>
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.saidaItemId}</td>
					<td>${item.entradaItem.produto.descricao}</td>
					<td>${item.quantidade}</td>
					<td>${item.unidadeDeMedida.sigla}</td>
					<td><a href="<c:url value='/saida/${saida.saidaId}/saidaitem/${item.saidaItemId}' />" class="btn btn-primary" >Editar</a> <a href="<c:url value='/saida/${saida.saidaId}/saidaitem/excluir/${item.saidaItemId}' />" class="btn btn-danger" >Excluir</a></td>
				</tr>
			</c:forEach>
			</table>
		</div>
		
	</c:if>
	
  </div>
</div>

<script src="${pageContext.request.contextPath}/resources/js/jquery.mask.min.js"></script>

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
            url : "/GestorEstoque/produto/"+sel.value+"/unidadesdaentrada",
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