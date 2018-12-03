package com.ufc.br.mvp.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Recebedor;
import com.ufc.br.mvp.service.RecebedorService;

@Controller
@Transactional
@RequestMapping("/recebedor")
public class RecebedorController {
	
	@Autowired
	private RecebedorService service; 
	
	@RequestMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("recebedor");
		mv.addObject("recebedor", new Recebedor());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Recebedor recebedor) {
		service.save(recebedor);
		ModelAndView mv = new ModelAndView("redirect:/recebedor/listar");
		return mv;
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable int id) {
		Recebedor recebedor = service.find(id);
		ModelAndView mv = new ModelAndView("recebedor");
		mv.addObject("recebedor", recebedor);
		
		return mv;
	}
	
	@GetMapping("/listar")
	public ModelAndView listar() {
		List<Recebedor> recebedores = service.findAll();
		ModelAndView mv = new ModelAndView("recebedores");
		if (!recebedores.isEmpty()) {
			mv.addObject("recebedores", recebedores);
		}
		return mv;
	}
	
	@RequestMapping("/excluir/{id}")
	public String excluir(@PathVariable int id) {
		service.delete(id);
		return "redirect:/recebedor/listar";
	}
}
