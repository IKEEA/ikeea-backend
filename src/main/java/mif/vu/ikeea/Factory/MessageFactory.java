package mif.vu.ikeea.Factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Component
public class MessageFactory {
    @Value("${app.front-end.url}")
    public String frontEndUrl;

    private static String FRONT_END_URL;

    public static String verifyEmail(String token) {
        try {
            URL baseUrl = new URL(FRONT_END_URL);
            URL url = new URL(baseUrl, "/register/" + token);

            return "Hello, you can verify your account by clicking: " + url.toString();
        } catch (MalformedURLException exception) {
            log.error("Exception during generating URL for email");

            return "Hello, please contact us";
        }
    }

    @Value("${app.front-end.url}")
    public void setFrontEndUrl(String frontEndUrl) {
        MessageFactory.FRONT_END_URL = frontEndUrl;
    }
}
