package com.ufc.br.mvp.service;

import java.time.LocalDate;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ufc.br.mvp.bean.Conta;

@Service
public class SimpleEmailService {
	
	@Autowired
    private JavaMailSender sender;
    @Autowired
    private ContaService contaService;
    
	public void sendEmail() throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
   	 	LocalDate today = LocalDate.now();
   	 	for (Conta conta : contaService.findAll()) {
			
   	 		if (conta.getNotificacao().isEqual(today)) {
				helper.setTo(conta.getUsuario().getEmail());
	            helper.setText(getText(conta));
	            helper.setSubject("Notificação");
	            
	            sender.send(message);
			}
		}
        
	}
    
    public String getText(Conta conta) {
    	return "A data de alerta para o pagamento da conta que você nos avisou chegou. \nA conta é esta: "+ conta.getDescricao() +
				", valor: " + conta.getValor() +
    			" \n Esperamos que dê tudo certo no seu pagamento, tenha um ótimo dia :D";
    }
    
}
