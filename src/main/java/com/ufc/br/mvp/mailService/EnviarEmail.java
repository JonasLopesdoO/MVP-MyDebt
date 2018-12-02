package com.ufc.br.mvp.mailService;
import  java.util.Properties ;
import  javax.mail.Message ;
import  javax.mail.MessagingException ;
import  javax.mail.PasswordAuthentication ;
import  javax.mail.Session ;
import  javax.mail.Transport ;
import  javax.mail.internet.InternetAddress ;
import  javax.mail.internet.MimeMessage ;

public class EnviarEmail {
	 private String emailDestinatario;

	    private String assunto;
	    private String msg;

	    public String getEmailDestinatario() {
	        return emailDestinatario;
	    }

	    public void setEmailDestinatario(String emailDestinatario) {
	        this.emailDestinatario = emailDestinatario;
	    }

	    public String getAssunto() {
	        return assunto;
	    }

	    public void setAssunto(String assunto) {
	        this.assunto = assunto;
	    }

	    public String getMsg() {
	        return msg;
	    }

	    public void setMsg(String msg) {
	        this.msg = msg;
	    }

	    public boolean enviarGmail() {
	        boolean retorno = false;
	        //Get the session object  
	        Properties props = new Properties();
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.socketFactory.port", "465");
	        props.put("mail.smtp.socketFactory.class",
	                "javax.net.ssl.SSLSocketFactory");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.port", "465");

	        Session s = Session.getDefaultInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {

	                        return new PasswordAuthentication("mydebtmd@gmail.com", "mydebt2018");//email e senha usuário 
	                    }
	                });

	        //compose message  
	        try {
	            MimeMessage message = new MimeMessage(s);
	            message.setFrom(new InternetAddress("mydebtmd@gmail.com"));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.emailDestinatario));

	            message.setSubject(this.assunto);
	            message.setContent(this.msg, "text/html; charset=utf-8");

	            //send message  
	            Transport.send(message);

	            retorno = true;

	        } catch (MessagingException e) {
	            retorno = false;
	            e.printStackTrace();
	        }
	        return retorno;
	    }
	    
	    public boolean enviarHotmail()
	    {
	        boolean retorno = false; 
	        Properties props = new Properties();
	            /** Parâmetros de conexão com servidor Hotmail */
	            props.put("mail.transport.protocol", "smtp");
	            props.put("mail.smtp.host", "smtp.live.com");
	            props.put("mail.smtp.socketFactory.port", "587");
	            props.put("mail.smtp.socketFactory.fallback", "false");
	            props.put("mail.smtp.starttls.enable", "true");
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.port", "587");

	            Session session = Session.getDefaultInstance(props,
	                        new javax.mail.Authenticator() {
	                             protected PasswordAuthentication getPasswordAuthentication() 
	                             {
	                                   return new PasswordAuthentication("ruben.junior.2@hotmail.com", "*******");
	                             }
	                        });
	            /** Ativa Debug para sessão */
	            session.setDebug(true);
	            try {

	                  Message message = new MimeMessage(session);
	                  message.setFrom(new InternetAddress("ruben.junior.2@hotmail.com")); //Remetente

	                  message.setRecipients(Message.RecipientType.TO, 
	                                    InternetAddress.parse("siedler@gmail.com")); //Destinatário(s)
	                  message.setSubject("Enviando email com JavaMail");//Assunto
	                  message.setText("Enviei este email utilizando JavaMail com minha conta Hotmail!");
	                  /**Método para enviar a mensagem criada*/
	                  Transport.send(message);
	                  System.out.println("Feito!!!");
	                  retorno = true;
	             } catch (MessagingException e) {
	                  throw new RuntimeException(e);
	            }
	            
	            return retorno;
	    
	    }
	    
	    public boolean enviarNotificacaoByEmail(String emailDest, String assunto, String mensagem) {
	    	this.emailDestinatario = emailDest;
	    	this.assunto = assunto;
	    	this.msg = mensagem;
	    	if(enviarGmail()) {
	    		return true;
	    	}
	    	
	    	return false;
	    }

}
