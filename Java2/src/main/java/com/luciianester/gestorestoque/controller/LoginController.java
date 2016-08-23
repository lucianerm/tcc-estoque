package com.luciianester.gestorestoque.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luciianester.gestorestoque.resources.login.Login;

@Controller
@RequestMapping(value="/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {

		model.addAttribute("objeto", new Login());
		
		return "login/login";

	}
	
	@RequestMapping("/logar")
	public String gravar(@ModelAttribute("objeto") Login objeto, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println(objeto.getNomeAcesso() + " - " + objeto.getSenha());
		request.getSession().setAttribute("usuario", "Leandro");
		
		return "redirect:/";
		
	}

}