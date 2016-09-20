package com.luciianester.gestorestoque.base;

import org.springframework.ui.Model;

public class ModelUtils {

	private Model model;

	public ModelUtils(Model model) {
		this.model = model;
	}
	
	public void setMensagem(MensagemTipo tipo, String texto) {
		model.addAttribute("mensagemTipo", tipo);
		model.addAttribute("mensagem", texto);
	}
	
	public void setMensagemErro(String texto) {
		this.setMensagem(MensagemTipo.ERRO, texto);
	}
	
	public void setMensagemAlerta(String texto) {
		this.setMensagem(MensagemTipo.ALERTA, texto);
	}
	
	public void setMensagemSalvouSucesso() {
		this.setMensagem(MensagemTipo.SALVOU_SUCESSO, "Resgistro gravado com sucesso.");
	}
	
}
