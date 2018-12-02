package com.ufc.br.mvp.mailService;

import java.time.LocalDate;
import java.util.ArrayList;

import com.ufc.br.mvp.bean.Conta;
import com.ufc.br.mvp.bean.Usuario;
import com.ufc.br.mvp.repository.UsuarioRepository;
import com.ufc.br.mvp.service.ContaService;
import com.ufc.br.mvp.service.UsuarioService;

public class RotinaDeNotificacao {
	private EnviarEmail enviarEmail;

	public static void main(String[] args) {

	}
	
	public boolean notificarUsuario(){
		UsuarioService usuarioService = new UsuarioService();
		ContaService contaService = new ContaService();
		ArrayList<Conta> contas = new ArrayList(contaService.findAll());
		LocalDate dataAgoraDate;
		for(int i = 0; i < contas.size(); i++) {
			if(contas.get(i).getVencimento().isAfter(LocalDate.now())) {
				
			}
		}
		
		return false;
	}
	
	public boolean mandarEmailDeAtrasoConta(Usuario usuario, Conta conta) {
		String mensagem = "A conta "+conta.getDescricao()+" está perto de vencer, pague-a antes do dia "+conta.getVencimento().getDayOfMonth()+"/"+conta.getVencimento().getMonthValue();
		
		return false;
	}
	

}
