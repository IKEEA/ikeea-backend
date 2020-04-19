package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Topic;

import java.util.Date;

public class GoalFactory {

    public static Goal createGoal(Date lastUpdated, String status, Topic topic, ApplicationUser user) {
        Goal goal = new Goal();

        goal.setLastUpdate(lastUpdated);
        goal.setStatus(status);
        goal.setTopic(topic);
        goal.setUser(user);

        return goal;
    }
}
