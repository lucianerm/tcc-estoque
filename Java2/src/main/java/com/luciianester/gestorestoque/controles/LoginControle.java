package com.luciianester.gestorestoque.controles;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luciianester.gestorestoque.base.MensagemTipo;
import com.luciianester.gestorestoque.base.RecursoGenerico;
import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Perfil;
import com.luciianester.gestorestoque.entidades.Usuario;
import com.luciianester.gestorestoque.enums.Situacao;
import com.luciianester.gestorestoque.recursos.login.Login;
import com.luciianester.gestorestoque.recursos.perfil.PerfilRecurso;
import com.luciianester.gestorestoque.recursos.usuario.UsuarioRecurso;
import com.luciianester.gestorestoque.enums.PerfilTipo;

@Controller
@RequestMapping(value="/login")
public class LoginControle {

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {

		model.addAttribute("objeto", new Login());
		
		return "login/login";

	}
	
	@RequestMapping("/logar")
	public String logar(@ModelAttribute("objeto") Login objeto, Model modelo, HttpServletRequest requisicao) throws Exception {
		
		DAO dao = new DAO();
		
		try (
			RecursoGenerico<Usuario> recursoUsuario = new UsuarioRecurso(dao);
			RecursoGenerico<Perfil> recursoPerfil = new PerfilRecurso(dao);
		) {

			Usuario usuario1 = (Usuario) recursoUsuario.getDao().createCriteria(Usuario.class)
				.add(Restrictions.eq("usuarioId", 1l))
				.uniqueResult();
			
			if (usuario1==null) {
				
				Perfil perfilAdm = (Perfil) recursoPerfil.getDao().createCriteria(Perfil.class)
						.add(Restrictions.eq("tipo", PerfilTipo.ADMINISTRADOR))
						.uniqueResult();
				
				if (perfilAdm==null) {
					perfilAdm = new Perfil();
					perfilAdm.setDescricao("Administrador");
					perfilAdm.setTipo(PerfilTipo.ADMINISTRADOR);
					recursoPerfil.getDao().gravar(perfilAdm);
				}
				
				usuario1 = new Usuario();
				usuario1.setNome("Admin");
				usuario1.setNomeDeAcesso("admin");
				usuario1.setSenha("123456");
				usuario1.setSituacao(Situacao.ATIVO);
				usuario1.setPerfil(perfilAdm);
				
				recursoUsuario.getDao().gravar(usuario1);
				
			}
			
			Usuario usuario = (Usuario) recursoUsuario.getDao().createCriteria(Usuario.class)
					.add(Restrictions.eq("nomeDeAcesso", objeto.getNomeAcesso()))
					.add(Restrictions.eq("senha", objeto.getSenha()))
					.uniqueResult();
			
			if (usuario!=null) {
				requisicao.getSession().setAttribute("usuario", usuario);
				return "redirect:/";
			} else {
				objeto.setSenha(null);
				modelo.addAttribute("objeto", objeto);
				modelo.addAttribute("mensagemTipo", MensagemTipo.ERRO);
				modelo.addAttribute("mensagem", "Usuário ou Senha inválido");
				return "/login/login";
			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	@RequestMapping("/deslogar")
	public String deslogar(HttpServletRequest requisicao) throws Exception {
		requisicao.getSession().setAttribute("usuario", null);
		return "redirect:/";
	}

}