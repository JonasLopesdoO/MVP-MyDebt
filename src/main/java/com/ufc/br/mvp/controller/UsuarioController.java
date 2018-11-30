package com.ufc.br.mvp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping("/cadastro")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@RequestMapping("/contas")
	public ModelAndView getMyContas(Usuario user) {
		//Pegar o usuario da sessão
		ModelAndView mv = new ModelAndView("contas");
		mv.addObject("contas", user.getContas());
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(Usuario usuario) {
		service.save(usuario);
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
}
