package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luciianester.gestorestoque.core.ControllerCadastroFilho;
import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.model.Produto;
import com.luciianester.gestorestoque.model.UnidadeDeMedida;

@Controller
@RequestMapping("/produto/{paiId}/unidadedemedida")
public class UnidadeDeMedidaController extends ControllerCadastroFilho<UnidadeDeMedida>{

	public UnidadeDeMedidaController() {
		super("unidadedemedida", new ResourceGenerico<>(UnidadeDeMedida.class));
	}

	@Override
	public Long getId(UnidadeDeMedida objeto) {
		return objeto.getUnidadeDeMedidaId();
	}

	@Override
	public void pesquisar() throws Exception {
		
		Produto produto = new ResourceGenerico<Produto>(Produto.class).listarPeloId(this.getPaiId());
		this.addAttribute("produto", produto);
		
	}

	@Override
	public void cadastrar() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editar(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String salvar(UnidadeDeMedida objeto) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
