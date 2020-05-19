package mif.vu.ikeea.Mailer;

import javax.enterprise.inject.Default;

@Default
public class EmailMessage implements EmailMessageInterface {
    @Override
    public String message(String text) {
        return "New information from IKEEA application for the user: ";
    }
}

