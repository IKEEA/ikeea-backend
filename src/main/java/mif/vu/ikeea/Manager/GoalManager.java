package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Repository.GoalRepository;
import mif.vu.ikeea.Entity.Repository.TopicRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.GoalFactory;
import mif.vu.ikeea.Payload.GoalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class GoalManager {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private GoalRepository goalRepository;

    public Goal create(GoalRequest goalRequest) {

        Optional<ApplicationUser> optionalUser = userRepository.findById(goalRequest.getUserId());
        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("User is empty");
        }

        ApplicationUser user = optionalUser.get();

        Optional<Topic> optionalTopic = topicRepository.findById(goalRequest.getTopicId());
        if (optionalTopic.isEmpty()) {
            throw new BadRequestHttpException("Topic is empty");
        }
        Topic topic = optionalTopic.get();

        Date lastUpdated = new Date();

        Goal goal = GoalFactory.createGoal(
                lastUpdated,
                goalRequest.getStatus(),
                topic,
                user
        );

        Goal result = goalRepository.save(goal);

        return result;
    }
}
