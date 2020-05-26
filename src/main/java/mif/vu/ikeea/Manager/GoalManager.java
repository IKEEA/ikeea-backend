package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Enums.EGoalStatus;
import mif.vu.ikeea.Factory.GoalFactory;
import mif.vu.ikeea.Mailer.EmailService;
import mif.vu.ikeea.Messages.IMessage;
import mif.vu.ikeea.Payload.GoalRequest;
import mif.vu.ikeea.Payload.UpdateGoalRequest;
import mif.vu.ikeea.RepositoryService.GoalService;
import mif.vu.ikeea.RepositoryService.TopicService;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class GoalManager {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private EmailService emailService;

    @Autowired
    @Qualifier("goal_added_message")
    private IMessage message;

    @Transactional
    public Goal create(GoalRequest goalRequest) {
        ApplicationUser user = userService.loadById(goalRequest.getUserId());
        Topic topic = topicService.loadById(goalRequest.getTopicId());

        Goal goal = GoalFactory.createGoal(
                new Date(),
                EGoalStatus.ASSIGNED,
                topic,
                user
        );

        Goal result = goalService.add(goal);

        return result;
    }

    @Transactional
    public void update(Goal goal, UpdateGoalRequest updateGoalRequest) {
        if (updateGoalRequest.getStatus() != null) {
            goal.setStatus(updateGoalRequest.getStatus());
        }

        goal.setLastUpdate(new Date());
        goalService.update(goal);
    }

    public void notifyUser(GoalRequest goalRequest) {
        ApplicationUser user = userService.loadById(goalRequest.getUserId());
        String emailMessage = message.createMessage();
        emailService.sendSimpleMessage(user.getEmail(), "New goal", emailMessage);
    }
}
