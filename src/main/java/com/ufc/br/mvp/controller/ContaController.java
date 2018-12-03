package com.ufc.br.mvp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.bean.Recebedor;
import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.service.ContaService;
import com.ufc.br.mvp.service.RecebedorService;
import com.ufc.br.mvp.service.UsuarioService;

@Controller
@Transactional
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService service;
	@Autowired
	private RecebedorService recebedorService;
	@Autowired
	private UsuarioService usuarioService;
	
	private static final Logger logger = Logger.getLogger(String.valueOf(ContaController.class));
	
	@RequestMapping("/formulario")
	public ModelAndView formulario() {
		ModelAndView mv = new ModelAndView("conta");
		List<Recebedor> recebedores = new ArrayList<Recebedor>();
		recebedores.addAll(recebedorService.findAll());
		mv.addObject("recebedores", recebedores);
		mv.addObject("conta", new Conta());
		return mv;
	}
	
	@PostMapping(path = "/salvar")
	public ModelAndView salvar(@RequestParam String descricao, @RequestParam double valor,
							@RequestParam String vencimento, @RequestParam String notificacao,
							@RequestParam Integer idRecebedor) {
		
		//Usuario logado na sessão
		Object auth = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails user = (UserDetails) auth;
				
		Usuario usuario = usuarioService.buscaPorEmail(user.getUsername());
		
		Conta conta = new Conta();
		Recebedor recebedor = recebedorService.find(idRecebedor);
		LocalDate dataVencimento;
		LocalDate dataNotificacao;
		
		conta.setDescricao(descricao);
		conta.setValor(valor);
		if (usuario != null) {
			conta.setUsuario(usuario);
		}
		if (recebedor != null) {
			conta.setRecebedor(recebedor);
		}
		try {
			dataVencimento = LocalDate.parse(vencimento);
			dataNotificacao = LocalDate.parse(notificacao);
			conta.setVencimento(dataVencimento);
			conta.setNotificacao(dataNotificacao);
		} catch (DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		
		service.save(conta);
		ModelAndView mv = new ModelAndView("redirect:/usuario/contas");
		return mv;
	}
	
	@PostMapping(path = "/atualizar")
	public ModelAndView atualiza(@RequestParam int id, @RequestParam String descricao, @RequestParam double valor,
							@RequestParam String vencimento, @RequestParam String notificacao,
							@RequestParam Integer idRecebedor) {
		
		Conta conta = service.find(id);
		Recebedor recebedor = recebedorService.find(idRecebedor);
		LocalDate dataVencimento;
		LocalDate dataNotificacao;
		
		conta.setDescricao(descricao);
		conta.setValor(valor);
		if (recebedor != null) {
			conta.setRecebedor(recebedor);
		}
		try {
			dataVencimento = LocalDate.parse(vencimento);
			dataNotificacao = LocalDate.parse(notificacao);
			conta.setVencimento(dataVencimento);
			conta.setNotificacao(dataNotificacao);
		} catch (DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		
		service.save(conta);
		ModelAndView mv = new ModelAndView("redirect:/usuario/contas");
		return mv;
	}
	
	@RequestMapping("/atualizar/{id}")
	public ModelAndView atualizar(@PathVariable int id) {
		Conta conta = service.find(id);
		ModelAndView mv = new ModelAndView("formulario-conta");
		mv.addObject("recebedores", recebedorService.findAll());
		mv.addObject("conta", conta);
		return mv;
	}
	
	@RequestMapping("/excluir/{id}")
	public String excluirConta(@PathVariable Integer id) {
		service.deleteConta(id);
		return "redirect:/usuario/contas";
	}
	
}
