package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Mailer.EmailMessageInterface;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class MessageFactory implements EmailMessageInterface {
    @Inject
    private EmailMessageInterface emailMessage;

    public String message(String text) {
        return emailMessage.message(text);
    }
}
