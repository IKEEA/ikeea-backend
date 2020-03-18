package mif.vu.ikeea.Mailer;

import mif.vu.ikeea.Factory.EmailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements EmailServiceInterface {
    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, String text) throws MailException {
        SimpleMailMessage emailMessage = EmailFactory.create(to, subject, text);

        mailSender.send(emailMessage);
    }
}
