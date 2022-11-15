package common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.Email;

public class EmailUtils {
	public static void send(Email email) throws Exception {
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        p.put("mail.smtp.port", 587);
		
		Session session = Session.getInstance(p, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email.getFrom(), email.getFromPassword());
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			
			message.setFrom(new InternetAddress(email.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
			message.setSubject(email.getSubject());
			message.setContent(email.getContent(), "text/html; charset=utf-8");
			
			Transport.send(message);			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
