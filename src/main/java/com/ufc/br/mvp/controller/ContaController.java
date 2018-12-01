package com.ufc.br.mvp.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.bean.Recebedor;
import com.ufc.br.mvp.service.ContaService;
import com.ufc.br.mvp.service.RecebedorService;

@Controller
@Transactional
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	private ContaService service;
	@Autowired
	private RecebedorService recebedorService;
	
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
							@RequestParam String vencimento, @RequestParam String pagamento,
							@RequestParam Integer idRecebedor) {
		
		Conta conta = new Conta();
		Recebedor recebedor = recebedorService.find(idRecebedor);
		LocalDate dataVencimento;
		LocalDate dataPagamento;
		
		conta.setDescricao(descricao);
		conta.setValor(valor);
		if (recebedor != null) {
			conta.setRecebedor(recebedor);
		}
		try {
			dataVencimento = LocalDate.parse(vencimento);
			dataPagamento = LocalDate.parse(pagamento);
			conta.setVencimento(dataVencimento);
			conta.setPagamento(dataPagamento);
		} catch (DateTimeParseException e) {
			logger.warning("Data no formato invalido");
		}
		
		service.save(conta);
		ModelAndView mv = new ModelAndView("redirect:/usuario/contas");
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
