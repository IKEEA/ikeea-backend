package mif.vu.ikeea.Mailer;

import lombok.extern.slf4j.Slf4j;
import mif.vu.ikeea.Factory.MessageFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Decorator
public class VerifyEmailMessage {

    @Inject
    @Delegate
    @Any
    MessageFactory emailMessage;

    @Value("${app.front-end.url}")
    public String frontEndUrl;

    private static String FRONT_END_URL;

    public String message(String token) {
        try {
            URL baseUrl = new URL(FRONT_END_URL);
            URL url = new URL(baseUrl, "/register/" + token);

            return emailMessage.message(token) + "Hello, you can verify your account by clicking: " + url.toString();
        } catch (MalformedURLException exception) {
            log.error("Exception during generating URL for email");

            return "Hello, please contact us";
        }
    }

    @Value("${app.front-end.url}")
    public void setFrontEndUrl(String frontEndUrl) {
        VerifyEmailMessage.FRONT_END_URL = frontEndUrl;
    }
}
