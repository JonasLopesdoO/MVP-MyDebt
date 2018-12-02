package com.ufc.br.mvp.mailService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.service.ContaService;

@WebListener
public class RotinaDeNotificacao implements ServletContextListener {
	private EnviarEmail enviarEmail;
	@Autowired
	private ContaService contaService; 

	public static void main(String[] args) {
		RotinaDeNotificacao r = new RotinaDeNotificacao();
		r.notificarUsuario();
	}
	
	public void contextInitializer(ServletContextEvent event) {
		RotinaDeNotificacao r = new RotinaDeNotificacao();
		r.notificarUsuario();
	}
	
	public boolean notificarUsuario(){
		List<Conta> contas = new ArrayList<Conta>();
		contas.addAll(contaService.findAll());
		
		for(int i = 0; i < contas.size(); i++) {
			if(contas.get(i).getVencimento().isAfter(LocalDate.of(LocalDate.now().getDayOfMonth()-3, LocalDate.now().getMonth(), LocalDate.now().getYear()))) {
				mandarEmailDeVencimentoConta(contas.get(i).getUsuario(), contas.get(i));
			}
		}
		
		return false;
	}
	
	public boolean mandarEmailDeVencimentoConta(Usuario usuario, Conta conta) {
		String mensagem = "A conta "+conta.getDescricao()+" está perto de vencer, pague-a antes do dia "+conta.getVencimento().getDayOfMonth()+"/"+conta.getVencimento().getMonthValue();
		if(enviarEmail.enviarNotificacaoByEmail(usuario.getEmail(), "Conta perto de vencer", mensagem)) {
			return true;
		}
		return false;
	}
	

}
