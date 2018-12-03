package com.ufc.br.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Boleto;
import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.service.ContaService;

@Controller
@RequestMapping("/boleto")
public class BoletoController {
	
	@Autowired
	private ContaService contaService;
	
	@PostMapping("/gerar")
	public void geraBoleto(@PathVariable int id) {
		
		ModelAndView mv = new ModelAndView("boleto");
		Conta conta = contaService.find(id);
		mv.addObject("conta", conta);
		mv.addObject("boleto", new Boleto());
		
	}
}
