package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Mailer.EmailMessageInterface;
import org.springframework.stereotype.Component;

@Component
public class MessageFactory implements EmailMessageInterface {
    public String message(String text) {
        return "New information from IKEEA application for the user: /n/n";
    }
}
