package com.ufc.br.mvp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ufc.br.mvp.mailService.RotinaDeNotificacao;

@Controller
public class PrincipalController {
	
	@RequestMapping(path= "/")
	public String pagina() {
		return "index";
	}
	
	
	@RequestMapping(path= "/contas-lista")
	public String paginaInicial() {
		System.out.println("Executando");
		return "contas-lista";
	}
}
