package br.edu.faculdadedelta.projetospringmvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.projetospringmvc.modelo.Genero;
import br.edu.faculdadedelta.projetospringmvc.service.GeneroService;

@Controller
@RequestMapping("/generos")
public class GeneroController {

	private static final String REDIRECT = "redirect:/";
	private static final String GENEROS = "generos";
	private static final String NOVO = "/novo";
	private final static String GENERO_CADASTRO = "generoCadastro";
	private final static String GENERO_LISTA = "generoLista";
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(GENERO_CADASTRO);
		modelAndView.addObject(new Genero());
		return modelAndView;
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Genero genero, Errors errors, RedirectAttributes redirectAttributes) {
		
		if(errors.hasErrors()) {
			return new ModelAndView(GENERO_CADASTRO);
		}
		
		if(genero.getId() == null) {
			generoService.incluir(genero);
			redirectAttributes.addFlashAttribute("mensagem","Inclusão realizada com sucesso!");
		}else {
			generoService.editar(genero);
			redirectAttributes.addFlashAttribute("mensagem","Alteração realizada com sucesso!");
		}
		return new ModelAndView(REDIRECT+GENEROS+NOVO);
	}
	
	@GetMapping
	public ModelAndView lista() {
		ModelAndView modelAndView = new ModelAndView(GENERO_LISTA);
		modelAndView.addObject(GENEROS,generoService.listar());
		return modelAndView;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(GENERO_CADASTRO);
		modelAndView.addObject(generoService.pesquisarPorId(id));
		return modelAndView;
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(REDIRECT+GENEROS);
		generoService.excluir(id);
		return modelAndView;
	}
}
