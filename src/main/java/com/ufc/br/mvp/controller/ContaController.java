package com.ufc.br.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.service.ContaService;

@Controller
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService service;
	
	@RequestMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("conta");
		mv.addObject("conta", new Conta());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Conta conta) {
		service.save(conta);
		ModelAndView mv = new ModelAndView("contas");
		return mv;
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable Long id) {
		Conta conta = service.find(id);
		ModelAndView mv = new ModelAndView("conta");
		mv.addObject("conta", conta);
		return mv;
	}
	
	@RequestMapping("/excluir/{id}")
	public ModelAndView excluirUsuario(@PathVariable Long id) {
		service.delete(id);
		ModelAndView mv = new ModelAndView("contas");
		return mv;
	}
}
