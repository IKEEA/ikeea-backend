package mif.vu.ikeea.Messages;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Component("invitation_message")
public class InvitationMessage implements IMessage {

    @Value("${app.front-end.url}")
    public String frontEndUrl;

    @Autowired
    public BaseMessage message;

    @Getter @Setter
    private String parameter;

    @Override
    public String createMessage() {
        try {
            URL baseUrl = new URL(frontEndUrl);
            URL url = new URL(baseUrl, "/register/" + getParameter());

            return message.createMessage() + "You can verify your account by clicking: " + url.toString();
        } catch (MalformedURLException exception) {
            log.error("Exception during generating URL for email");

            return "Please contact our support";
        }
    }
}
