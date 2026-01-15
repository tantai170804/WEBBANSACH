package me.huu_thinh.main.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import me.huu_thinh.main.util.JavaMail;
import me.huu_thinh.main.util.PasswordEncoding;

public class MailService {
	
	 private Session createSession() {
	        Properties props = JavaMail.getMailProperties();

	        return Session.getInstance(props, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(
	                		JavaMail.getUsername(),
	                        JavaMail.getPassword()
	                );
	            }
	        });
	    }
	public void sendEmail(String to, String subject, String content) {
        try {
            MimeMessage message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(JavaMail.getUsername()));
            message.setRecipients(
            		MimeMessage.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setContent(content, "text/html; charset=UTF-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Send email failed", e);
        }
    }
	public void sendForgotPassword(String email,String password_hash) {
		 if(email.isBlank()) return;
		  String password = PasswordEncoding.decodingPassword(password_hash);
		  String subject = "Web bán sách gửi lại mật khẩu";
		  String content = """
		            <h3>Chào bạn,</h3>
		            <p>Mật khẩu của bạn là:</p>
		            <a>%s</a>
		        """.formatted(password);
		  sendEmail(email,subject,content);
	}
}
