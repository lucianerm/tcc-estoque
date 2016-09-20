package com.luciianester.gestorestoque.base.filtro;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.luciianester.gestorestoque.base.dao.DAO;
import com.luciianester.gestorestoque.entidades.Privilegio;
import com.luciianester.gestorestoque.entidades.Usuario;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.enums.Tela;
import com.luciianester.gestorestoque.recursos.perfil.PerfilRecurso;

public class FiltroDeRequisicao implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@SuppressWarnings("resource")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		boolean login = false;
		
		try {
			
			HttpServletRequest requisicao = (HttpServletRequest) request;
			HttpSession sessao = requisicao.getSession();
			
			String metodo = requisicao.getRequestURI().replaceAll(requisicao.getContextPath(), "");
			
			List<Tela> telas = new ArrayList<>();
			
			if (!(metodo.indexOf("resources")>=0) && !(metodo.indexOf("login")>=0)) {
				
				Object usuarioObj = sessao.getAttribute("usuario");
				
				if (usuarioObj==null) {
					
					requisicao.getRequestDispatcher("/login").forward(request, response);
					login = true;
					
				} else {
					
					Usuario usuario = (Usuario) usuarioObj;
					
					if (usuario.getPerfil().getTipo().equals(PerfilTipo.ADMINISTRADOR)) {
						
						sessao.setAttribute("telaAcessoAlterar", true);
						sessao.setAttribute("telaAcessoExcluir", true);
						telas = new ArrayList<Tela>(Arrays.asList(Tela.values()));
						for (Tela tela : telas) {
							if (metodo.indexOf(tela.getCaminho())>=0) {
								sessao.setAttribute("telaAcessoCaminho", tela.getCaminho());
								sessao.setAttribute("telaAcessoNome", tela.getNome());
								break;
							}
						}
						
					} else {
						
						List<Privilegio> privilegios = new PerfilRecurso(new DAO()).listPrivilegiosPeloPerfil(usuario.getPerfil());
						for (Privilegio privilegio : privilegios) {
							if (!telas.contains(privilegio.getTela())) {
								telas.add(privilegio.getTela());
							}
						}
						
						boolean temAcesso = true;
						if (!metodo.equals("/") && !metodo.equals("")) {
							
							temAcesso = false;
							for (Privilegio privilegio : privilegios) {
								if (metodo.indexOf(privilegio.getTela().getCaminho())>=0) {
									
									sessao.setAttribute("telaAcessoCaminho", privilegio.getTela().getCaminho());
									sessao.setAttribute("telaAcessoNome", privilegio.getTela().getNome());
									
									sessao.setAttribute("telaAcessoAlterar", privilegio.isAlterar());
									sessao.setAttribute("telaAcessoExcluir", privilegio.isExcluir());
									
									if (requisicao.getMethod().equals("GET")) {
										
										if (metodo.indexOf("excluir")>=0) {
											temAcesso = privilegio.isExcluir();
										} else {
											temAcesso = true;
										}
										
									} else if (requisicao.getMethod().equals("POST")) {
										
										temAcesso = privilegio.isAlterar();
										
									}
									
									break;
								}
							}
						}
						
						if (!temAcesso) {
							request.setAttribute("errorMessage", "usuário sem acesso");
							request.getRequestDispatcher("/WEB-INF/views/error/erro.jsp").forward(request, response);
						}
						
					}
					
				}
				
			}
			
			sessao.setAttribute("telasAcesso", telas);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (!login) {
			try {
				chain.doFilter(request, response);
			} catch (Exception ex) {
				ex.printStackTrace();
				request.setAttribute("errorMessage", ex);
				request.getRequestDispatcher("/WEB-INF/views/error/erro.jsp")
	                               .forward(request, response);
			}
		}

	}

}