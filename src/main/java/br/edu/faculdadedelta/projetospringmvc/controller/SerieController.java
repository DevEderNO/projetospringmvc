package br.edu.faculdadedelta.projetospringmvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.faculdadedelta.projetospringmvc.modelo.Genero;
import br.edu.faculdadedelta.projetospringmvc.modelo.Serie;
import br.edu.faculdadedelta.projetospringmvc.modelo.tipo.Status;
import br.edu.faculdadedelta.projetospringmvc.service.GeneroService;
import br.edu.faculdadedelta.projetospringmvc.service.SerieService;

@Controller
@RequestMapping("/series")
public class SerieController {
	
	private static final String NOVO = "/novo";
	private static final String REDIRECT = "redirect:/";
	private static final String SERIES = "series";
	private final static String SERIE_CADASTRO = "serieCadastro";
	private final static String SERIE_LISTA = "serieLista";
	
	@Autowired
	private SerieService serieService;
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(NOVO)
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(SERIE_CADASTRO);
		modelAndView.addObject(new Serie());
		return modelAndView;
		
	}
	
	@ModelAttribute(name = "todosGeneros")
	public List<Genero> todosGeneros(){
		return generoService.listar();
	}
	
	@ModelAttribute(name = "todosStatus")
	public Status[] todosStatus(){
		return Status.values();
	}
	
	@PostMapping
	public ModelAndView salvar(@Valid Serie serie,Errors errors,RedirectAttributes redirectAttributes) {
		
		if(errors.hasErrors()) {
			return new ModelAndView(SERIE_CADASTRO);
		}
		if(serie.getId() == null) {
			serieService.incluir(serie);
			redirectAttributes.addFlashAttribute("mensagem","Inclusão realizada com sucesso!");
		}else {
			serieService.editar(serie);
			redirectAttributes.addFlashAttribute("mensagem","Alteração realizada com sucesso");
		}
		return new ModelAndView(REDIRECT+SERIES+NOVO);
	}
	
	@GetMapping
	public ModelAndView lista(){
		ModelAndView modelAndView = new ModelAndView(SERIE_LISTA);
		modelAndView.addObject(SERIES,serieService.listar());
		return modelAndView;
	}
	
	@GetMapping("/editar/{id}")
	public ModelAndView editar(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(SERIE_CADASTRO);
		modelAndView.addObject(serieService.pesquisarPorId(id));
		return modelAndView;
		
	}
	
	@GetMapping("/excluir/{id}")
	public ModelAndView excluir(@PathVariable("id") Long id) {
		ModelAndView modelAndView = new ModelAndView(REDIRECT+SERIES);
		serieService.excluir(id);
		return modelAndView;
	}
}
