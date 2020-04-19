package mif.vu.ikeea.Factory;

import org.springframework.mail.SimpleMailMessage;

public class EmailFactory {

    public static SimpleMailMessage create(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        return message;
    }
}
