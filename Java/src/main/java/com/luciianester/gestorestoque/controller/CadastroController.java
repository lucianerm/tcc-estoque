package com.luciianester.gestorestoque.controller;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luciianester.gestorestoque.core.dao.DAO;
import com.luciianester.gestorestoque.model.Usuario;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
	
	private DAO<Usuario> dao = new DAO<>();
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		
		try {
			
			Usuario usuario = new Usuario();
			
			List<Usuario> listPersons = dao.getSession().createCriteria(Usuario.class).list();
			
			model.addAttribute("person", usuario);
			model.addAttribute("listPersons", listPersons);
			
			return "teste/cadastro";
			
		} catch (Exception e) {
			throw e;
		}
		
		
	}
	
	@RequestMapping(value= "/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Usuario p){
		
		if (p.getId()==null || p.getId().equals(0)) {
			//new person, add it
			dao.save(p);
		}else{
			//existing person, call update
			dao.update(p);
		}
		
		return "redirect:/cadastro/index";
		
	}
	
	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") Long id){
		
        
		Usuario usuario = (Usuario) dao.getSession().createCriteria(Usuario.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		
		dao.delete(usuario);
		
        return "redirect:/cadastro/index";
    }
	
	@RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") Long id, Model model){

		Usuario usuario = (Usuario) dao.getSession().createCriteria(Usuario.class)
				.add(Restrictions.idEq(id))
				.uniqueResult();
		
		List<Usuario> listPersons = dao.getSession().createCriteria(Usuario.class).list();
		
		model.addAttribute("person", usuario);
		model.addAttribute("listPersons", listPersons);
		
		return "teste/cadastro";
		
    }

}
