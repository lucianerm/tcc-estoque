package com.luciianester.gestorestoque.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luciianester.gestorestoque.core.ResourceGenerico;
import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.Situacao;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.model.Perfil;
import com.luciianester.gestorestoque.model.Usuario;
import com.luciianester.gestorestoque.resources.login.Login;
import com.luciianester.gestorestoque.resources.perfil.PerfilResources;
import com.luciianester.gestorestoque.resources.usuario.UsuarioResources;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {

		model.addAttribute("objeto", new Login());
		
		return "login/login";

	}
	
	@RequestMapping("/logar")
	public String logar(@ModelAttribute("objeto") Login objeto, Model model, HttpServletRequest request) throws Exception {
		
		DAO dao = new DAO();
		
		try (
			ResourceGenerico<Usuario> res = new UsuarioResources(dao);
			ResourceGenerico<Perfil> resPerfil = new PerfilResources(dao);
		) {

			Usuario usuario1 = (Usuario) res.getDao().createCriteria(Usuario.class)
				.add(Restrictions.eq("usuarioId", 1l))
				.uniqueResult();
			
			if (usuario1==null) {
				
				Perfil perfilAdm = (Perfil) resPerfil.getDao().createCriteria(Perfil.class)
						.add(Restrictions.eq("tipo", PerfilTipo.ADMINISTRADOR))
						.uniqueResult();
				
				if (perfilAdm==null) {
					perfilAdm = new Perfil();
					perfilAdm.setDescricao("Administrador");
					perfilAdm.setTipo(PerfilTipo.ADMINISTRADOR);
					resPerfil.getDao().gravar(perfilAdm);
				}
				
				usuario1 = new Usuario();
				usuario1.setNome("Admin");
				usuario1.setNomeDeAcesso("admin");
				usuario1.setSenha("123456");
				usuario1.setSituacao(Situacao.ATIVO);
				usuario1.setPerfil(perfilAdm);
				
				res.getDao().gravar(usuario1);
				
			}
			
			Usuario usuario = (Usuario) res.getDao().createCriteria(Usuario.class)
					.add(Restrictions.eq("nomeDeAcesso", objeto.getNomeAcesso()))
					.add(Restrictions.eq("senha", objeto.getSenha()))
					.uniqueResult();
			
			if (usuario!=null) {
				request.getSession().setAttribute("usuario", usuario);
				return "redirect:/";
			} else {
				objeto.setSenha(null);
				model.addAttribute("objeto", objeto);
				return "/login/login";
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/deslogar")
	public String deslogar(HttpServletRequest request) throws Exception {
		request.getSession().setAttribute("usuario", null);
		return "redirect:/";
	}

}