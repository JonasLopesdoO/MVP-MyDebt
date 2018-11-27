package com.ufc.br.mvp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Usuario;

@Controller
public class UsuarioController {
	
	
	@RequestMapping("/lista-contas")
	public ModelAndView getMyContas(Usuario user) {
		//Pegar o usuario da sessão
		ModelAndView mv = new ModelAndView("lista-contas");
		mv.addObject("salas", user.getContas());
		return mv;
	}
}
