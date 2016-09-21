package com.luciianester.gestorestoque.base;

import java.util.List;

import org.springframework.ui.Model;

public class UtilidadesDoModelo {
	
	private Model modelo;
	
	public void setModelo(Model modelo) {
		this.modelo = modelo;
	}
	
	public Model getModelo() {
		return modelo;
	}
	
	protected void addAtributo(String nome, Object objeto) {
		this.getModelo().addAttribute(nome, objeto);
	}
	
	protected void setLista(List<?> lista) {
		this.addAtributo("lista", lista);
	}
	
	protected void setObjeto(Object objeto) {
		this.addAtributo("objeto", objeto);
	}
	
	public void setMensagem(MensagemTipo tipo, String texto) {
		this.getModelo().addAttribute("mensagemTipo", tipo);
		this.getModelo().addAttribute("mensagem", texto);
	}
	
	public void setMensagemErro(String texto) {
		this.setMensagem(MensagemTipo.ERRO, texto);
	}

	public void setMensagemObrigatorio(String texto) {
		this.setMensagem(MensagemTipo.ERRO, texto+" é obrigatório");
	}
	
	public void setMensagemSalvouSucesso() {
		this.setMensagem(MensagemTipo.SALVOU_SUCESSO, "Resgistro gravado com sucesso.");
	}

}
