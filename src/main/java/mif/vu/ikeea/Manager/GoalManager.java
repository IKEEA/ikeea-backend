package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Repository.GoalRepository;
import mif.vu.ikeea.Entity.Repository.TopicRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Enums.EGoalStatus;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.GoalFactory;
import mif.vu.ikeea.Payload.GoalRequest;
import mif.vu.ikeea.Payload.UpdateGoalRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Goal create(GoalRequest goalRequest) {

        Optional<ApplicationUser> optionalUser = userRepository.findById(goalRequest.getUserId());

        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("User is empty");
        }

        Optional<Topic> optionalTopic = topicRepository.findById(goalRequest.getTopicId());

        if (optionalTopic.isEmpty()) {
            throw new BadRequestHttpException("Topic is empty");
        }

        Goal goal = GoalFactory.createGoal(
                new Date(),
                EGoalStatus.ASSIGNED,
                optionalTopic.get(),
                optionalUser.get()
        );

        Goal result = goalRepository.save(goal);

        return result;
    }

    @Transactional
    public Goal update(Goal goal, UpdateGoalRequest updateGoalRequest) {

        if (updateGoalRequest.getStatus() != null) {
            goal.setStatus(updateGoalRequest.getStatus());
        }

        Date date = new Date();
        goal.setLastUpdate(date);

        Goal result = goalRepository.save(goal);

        return result;
    }
}
