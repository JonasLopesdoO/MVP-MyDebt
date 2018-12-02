package com.ufc.br.mvp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.bean.Role;
import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.service.RoleService;
import com.ufc.br.mvp.service.UsuarioService;

@Controller
@Transactional
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService service;
	
	@Autowired
	private RoleService roleService;
	
	private static final Logger logger = Logger.getLogger(String.valueOf(UsuarioController.class));
	
	
	@RequestMapping("/cadastro")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("cadastro");
		mv.addObject("usuario", new Usuario());
		return mv;
	}
	
	@RequestMapping("/contas")
	public ModelAndView getMyContas() {
		
		//Usuario logado na sessão
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
				
		Usuario usuario = service.buscaPorEmail(user.getUsername());
		List<Conta> contas = usuario.getContas();
		
		ModelAndView mv = new ModelAndView("contas");
		//if (usuario != null) {
			mv.addObject("contas", contas);
		//}
		return mv;
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvar(@RequestParam String nome, @RequestParam String nascimento,
							@RequestParam String email, @RequestParam String senha) {
		
		Usuario usuario = new Usuario();
		Role role = roleService.find("ROLE_USER");
		
		LocalDate dataNascimento;		
		String cripitografia = new BCryptPasswordEncoder().encode(senha);
		
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setSenha(cripitografia);
		usuario.getRoles().add(role);
		try {
			dataNascimento = LocalDate.parse(nascimento);
			usuario.setNascimento(dataNascimento);
			
		} catch (DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		
		service.save(usuario);
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
	
}
