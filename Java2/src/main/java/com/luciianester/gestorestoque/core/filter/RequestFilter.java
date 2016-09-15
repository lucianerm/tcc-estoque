package com.luciianester.gestorestoque.core.filter;

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

import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.enums.PerfilTipo;
import com.luciianester.gestorestoque.enums.Tela;
import com.luciianester.gestorestoque.model.Usuario;
import com.luciianester.gestorestoque.resources.perfil.PerfilResources;

public class RequestFilter implements Filter {

	@Override
	public void destroy() {
		// ...
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		boolean login = false;
		
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			//HttpServletResponse res = (HttpServletResponse) response; 
			
			HttpSession session = req.getSession();
			
			String metodo = req.getRequestURI().replaceAll(req.getContextPath(), "");
			
			List<Tela> telas = new ArrayList<>();
			
			if (!(metodo.indexOf("resources")>=0) && !(metodo.indexOf("login")>=0)) {
				
				
				Object usuario = session.getAttribute("usuario");
				
				if (usuario==null) {
					
					req.getRequestDispatcher("/login").forward(request, response);
					login = true;
					
				} else {
					
					Usuario user = (Usuario) usuario;
					
					if (user.getPerfil().getTipo().equals(PerfilTipo.ADMINISTRADOR)) {
						
						telas = new ArrayList<Tela>(Arrays.asList(Tela.values()));
						
					} else {
						
						telas = new PerfilResources(new DAO()).listTelasByPerfil(user.getPerfil());
						
					}
					
				}
				
			}
			
			session.setAttribute("telasAcesso", telas);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (!login) {
			try {
				chain.doFilter(request, response);
			} catch (Exception ex) {
				ex.printStackTrace();
				request.setAttribute("errorMessage", ex);
				request.getRequestDispatcher("/WEB-INF/views/jsp/error.jsp")
	                               .forward(request, response);
			}
		}

	}

}