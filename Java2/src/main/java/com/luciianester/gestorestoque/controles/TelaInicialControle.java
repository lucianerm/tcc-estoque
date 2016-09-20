package com.luciianester.gestorestoque.controles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class TelaInicialControle {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getTela() {

		ModelAndView modelo = new ModelAndView("telainicial/telainicial");
	
		return modelo;

	}

}