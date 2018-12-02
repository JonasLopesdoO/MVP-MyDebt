package com.ufc.br.mvp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PrincipalController {
	
	@RequestMapping(path= "/")
	public String index() {
		return "index";
	}
	
	@RequestMapping(path= "/login")
	public String pagina() {
		return index();
	}
	
	@RequestMapping(path= "/contas-lista")
	public String paginaInicial() {
		System.out.println("Executando");
		return "contas-lista";
	}
}
