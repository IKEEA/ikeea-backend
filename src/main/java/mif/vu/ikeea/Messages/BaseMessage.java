package mif.vu.ikeea.Messages;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class BaseMessage implements IMessage {

    @Getter
    @Setter
    private String parameter;

    @Override
    public String createMessage() {
        return "New information from IKEEA application for the user: ";
    }
}
