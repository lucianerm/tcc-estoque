package com.luciianester.gestorestoque.core.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			HttpServletResponse res = (HttpServletResponse) response; 
			
			
			String metodo = req.getRequestURI().replaceAll(req.getContextPath(), "");
			
			if (!(metodo.indexOf("resources")>=0) && !(metodo.indexOf("login")>=0)) {
				
				HttpSession session = req.getSession();
				
				Object usuario = session.getAttribute("usuario");
				
				
				if (usuario==null) {
					
					req.getRequestDispatcher("/login").forward(request, response);
					login = true;
					
				}
				
				/*
				HttpSession session = req.getSession();
				
				Object usuario = session.getAttribute("usuario");
				
				
				if (usuario==null) {
					usuario = "user " + new Date().getTime();
				}
				
				session.setAttribute("usuario", usuario);
				
				System.out.println("usuario: " + usuario);
				System.out.println("getRequestedSessionId: " + req.getRequestedSessionId());
				System.out.println("fim ");
				
				
		        
				
				/*
				System.out.println("metodo: " + metodo);
				String usuario = req.getHeader("usuario");
				String userAgent = req.getHeader("user-agent");
				
				System.out.println("usuario: " + usuario);
				System.out.println("userAgent: " + userAgent);
				
				if (usuario==null) {
					usuario = "user " + new Date().getTime();
				}
				
				res.setHeader("usuario", usuario);
				*/
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if (!login) {
			try {
				chain.doFilter(request, response);
			} catch (Exception ex) {
				request.setAttribute("errorMessage", ex);
				request.getRequestDispatcher("/WEB-INF/views/jsp/error.jsp")
	                               .forward(request, response);
			}
		}

	}

}