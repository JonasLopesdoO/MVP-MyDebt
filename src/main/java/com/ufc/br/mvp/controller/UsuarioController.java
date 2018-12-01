package com.ufc.br.mvp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	private static final Logger logger = Logger.getLogger(String.valueOf(UsuarioController.class));
	
	
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
	public ModelAndView salvar(@RequestParam String nome, @RequestParam String nascimento,
							@RequestParam String login, @RequestParam String senha) {
		
		Usuario usuario = new Usuario();
		
		LocalDate dataNascimento;		
		String cripitografia = new BCryptPasswordEncoder().encode(senha);
		
		usuario.setNome(nome);
		usuario.setLogin(login);
		usuario.setSenha(cripitografia);
		
		try {
			dataNascimento = LocalDate.parse(nascimento);
			usuario.setNascimento(dataNascimento);
			
		} catch (DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		
		service.save(usuario);
		ModelAndView mv = new ModelAndView("redirect:/");
		return mv;
	}
}
