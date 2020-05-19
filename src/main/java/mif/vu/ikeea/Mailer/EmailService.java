package mif.vu.ikeea.Mailer;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Factory.EmailFactory;
import mif.vu.ikeea.Factory.MessageFactory;
import mif.vu.ikeea.Manager.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class EmailService implements EmailServiceInterface {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserManager userManager;

    @Autowired
    EmailService emailService;

    @Autowired
    MessageFactory messageFactory;

    public void sendSimpleMessage(String to, String subject, String text) throws MailException {
        SimpleMailMessage emailMessage = EmailFactory.create(to, subject, text);

        mailSender.send(emailMessage);
    }

    public void createMessage(String email, Authentication authentication) {
        ApplicationUser manager = (ApplicationUser) authentication.getPrincipal();

        ApplicationUser user = userManager.create(email, manager);
        String message = messageFactory.message(user.getToken());
        emailService.sendSimpleMessage(user.getEmail(), "Verify your account", message);
    }

}
