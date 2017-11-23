package com.spring.mytest02.common.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	@Autowired
	 private JavaMailSender mailSender;
   @Autowired
   private SimpleMailMessage preConfiguredMessage;
	
	@Async
	public void sendMail(String to, String subject, String body)
	{
		MimeMessage message = mailSender.createMimeMessage();
   	 
  	  try {
  	   MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
  	   messageHelper.setSubject(subject);
  	   messageHelper.setTo(to);
  	   messageHelper.setCc("frogfrog1106@naver.com");
  	   messageHelper.setFrom("frogfrog1106@naver.com");
  	   messageHelper.setText(body,true );
  	   mailSender.send(message);
  	  }catch(Exception e){
  		  e.printStackTrace();
  	  }
		
		/*SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);*/
	}
	
	@Async
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage=new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}

}
