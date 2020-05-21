package mif.vu.ikeea.Messages;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component("goal_added_message")
public class GoalAddedMessage implements IMessage {

    @Autowired
    public BaseMessage message;

    @Getter
    @Setter
    private String parameter;

    @Override
    public String createMessage() {
        return message.createMessage() + "You have new goals. Please check on website";
    }
}
