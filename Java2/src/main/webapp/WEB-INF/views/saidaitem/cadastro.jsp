<%@page import="com.luciianester.gestorestoque.entidades.Saida"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	<%@include file="../../base/mensagem.jsp" %>
	
	<form:form action="<%= acao%>" commandName="objeto" autocomplete="off">
	
		<br/>
		<form:input path="saidaItemId" readonly="true" size="8"  disabled="true" />
		<form:hidden path="saidaItemId" />
		<br/>
		<br/>
		
		<label>Produto:</label>
		<br/>
		<select name="objeto.produtoId" onchange="onSelectedProduto(this);" class="cmbProduto js-example-basic-single js-states form-control">
			<option value="0" ${objeto.produtoId == null ? 'selected' : ''}>Selecione um Produto</option>
			<c:forEach items="${listaProdutos}" var="item">
				<option value="${item.produtoId}" ${item.produtoId == objeto.produtoId ? 'selected' : ''}>${item.produtoId} - ${item.descricao}</option>
			</c:forEach>
		</select>
		<br/>
		<br/>
		
		<label>Entradas:</label>
		<br/>
		<select id="cmbEntradaItem" name="entradaItem.entradaItemId"  class="cmbEntradaItem js-example-basic-single js-states form-control">
			<option value="0" ${objeto.entradaItem.entradaItemId == null ? 'selected' : ''}>Selecione uma Entrada</option>
			<c:forEach items="${listaEntradaItem}" var="item">
				<option value="${item.entradaItemId}" ${item.entradaItemId == objeto.entradaItem.entradaItemId ? 'selected' : ''}>${item.entradaItemId} - ${item.produto.produtoId} - ${item.produto.descricao} - ${item.saldo} ${item.sigla}</option>
			</c:forEach>	
		</select>
		<br/>
		<br/>
		
		<label>Unidade de Medida:</label>
		<br/>
		<select id="cmbUnidadeDeMedida" onchange="onSelectedUnidade(this);" name="unidadeDeMedida.unidadeDeMedidaId" class="cmbUnidadeDeMedida js-example-basic-single js-states form-control">
			<option value="0" ${objeto.unidadeDeMedida.unidadeDeMedidaId == null ? 'selected' : ''}>Selecione uma Unidade de Medida</option>
			<c:forEach items="${listaUnidadeDeMedida}" var="item">
				<option value="${item.unidadeDeMedidaId}" ${item.unidadeDeMedidaId == objeto.unidadeDeMedida.unidadeDeMedidaId ? 'selected' : ''}>${item.sigla} - ${item.descricao} - R$ ${item.valorDeVenda}</option>
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
		<form:input id="txtValor" path="valor" readonly="true" size="8"  disabled="true" />
		<form:hidden path="valor" />
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
				<th width="80">Código</th>
				<th width="200">Produto</th>
				<th width="80">Quantidade</th>
				<th width="80">UM</th>
				<th width="100">Ações</th>
			</tr>
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.saidaItemId}</td>
					<td>${item.entradaItem.produto.descricao}</td>
					<td><fmt:formatNumber value="${item.quantidade}" minFractionDigits="2"/></td>
					<td>${item.unidadeDeMedida.sigla}</td>
					<td><fmt:formatNumber value="${item.valor}" minFractionDigits="2"/></td>
					<td><fmt:formatNumber value="${item.total}" minFractionDigits="2"/></td>
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
		
		$(".cmbEntradaItem").select2({
			placeholder: "Selecione uma Entrada"
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
                   	option.value = 0;
					option.text = "Selecione uma Unidade de Medida";
					option.selected = true;
					comboBox.add(option);
                      
                    for (var i = 0; i < data.lista.length; i++) {
                    	var option = document.createElement("option");
                    	option.value = data.lista[i].unidadeDeMedidaId;
                        option.text = data.lista[i].sigla + " - " + data.lista[i].descricao + " - R$ " + data.lista[i].valorDeVenda;
                        comboBox.add(option);
                    }
                    
                },
				404 : function(){
		            alert("Deu Erro");
		        }
            }
        });
		
		$.ajax({
            method : "get",
            dataType : "json",
            url : "/GestorEstoque/produto/"+sel.value+"/entradas",
            statusCode : {
                200 : function(data){
                    
                    var comboBox = document.getElementById("cmbEntradaItem");
                    
                    while (comboBox.options.length > 0) {                
                        comboBox.remove(0);
                    }
                    
                   	var option = document.createElement("option");
                   	option.value = 0;
					option.text = "Selecione uma Entrada";
					option.selected = true;
					comboBox.add(option);
                      
                    for (var i = 0; i < data.lista.length; i++) {
                    	var option = document.createElement("option");
                    	option.value = data.lista[i].entradaItemId;
                        option.text = data.lista[i].entradaItemId + " - " + data.lista[i].produto.descricao + " - " + data.lista[i].saldo + " " + data.lista[i].sigla;
                        comboBox.add(option);
                    }
                    
                },
				404 : function(){
		            alert("Deu Erro");
		        }
            }
        });
	}
	
	function onSelectedUnidade(sel) {
		
		$.ajax({
            method : "get",
            dataType : "json",
            url : "/GestorEstoque/produto/0/unidadedemedida/"+sel.value+"/valor",
            statusCode : {
                200 : function(data){
                    
                	var txtValor = document.getElementById("txtValor");
            		txtValor.value = ""+data;
            		
                },
				404 : function(){
		            alert("Deu Erro");
		        }
            }
        });
		
	}

</script>