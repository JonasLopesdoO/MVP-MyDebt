package com.ufc.br.mvp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.service.ContaService;

public class PrincipalController {
	
	@Autowired
	private ContaService service;
	
	@RequestMapping("contas-lista")
	public ModelAndView paginaInicial() {
		List<Conta> contas = service.getMyContas();
		ModelAndView mv = new ModelAndView("contas-lista");
		
		mv.addObject("contas", contas);
		return mv;
	}
}
