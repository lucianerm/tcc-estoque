<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<h1>
	Testes Login
</h1>



<form:form action="/GestorEstoque/login/logar" commandName="objeto">
	
	<br/>
	<label>Código:</label>
	<br/>
	<form:input path="nomeAcesso" />
	<br/>
	<br/>
	<label>Descrição:</label>
	<br/>
	<form:input path="senha" />
	<br/>
	<br/>
	<input type="submit" value="Salvar" />
	
</form:form>
