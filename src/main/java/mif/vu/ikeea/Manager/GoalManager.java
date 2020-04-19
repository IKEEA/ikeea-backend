package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Factory.GoalFactory;
import mif.vu.ikeea.Payload.GoalRequest;
import mif.vu.ikeea.Payload.UpdateGoalRequest;
import mif.vu.ikeea.RepositoryService.GoalService;
import mif.vu.ikeea.RepositoryService.TopicService;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public Goal create(GoalRequest goalRequest) {
        ApplicationUser user = userService.loadById(goalRequest.getUserId());
        Topic topic = topicService.loadById(goalRequest.getTopicId());

        Goal goal = GoalFactory.createGoal(
                new Date(),
                goalRequest.getStatus(),
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
}
