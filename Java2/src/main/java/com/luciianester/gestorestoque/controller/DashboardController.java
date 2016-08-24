package com.luciianester.gestorestoque.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class DashboardController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getData() {

		ModelAndView model = new ModelAndView("dashboard/dashboard");
	
		return model;

	}

}