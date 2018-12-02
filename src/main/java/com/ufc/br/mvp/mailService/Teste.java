package com.ufc.br.mvp.mailService;

public class Teste {
	public static void main(String [] args) {
		EnviarEmail gmail = new EnviarEmail();
		gmail.setEmailDestinatario("gabriel.limaqxda@gmail.com");
		gmail.setAssunto("teste do app");
		gmail.setMsg("Esta é uma mensagem de teste");
		
		if(gmail.enviarGmail()) {
			System.out.println("Deu certo");
		}
		
		
		
	}
}
