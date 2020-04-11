package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;

import java.util.Date;

public class LearningDayFactory {

    public static LearningDay createLearningDay(String title, Date date, Topic topic, ApplicationUser user) {
        LearningDay learningDay = new LearningDay();

        learningDay.setTitle(title);
        learningDay.setDate(date);
        learningDay.setTopic(topic);
        learningDay.setUser(user);

        return learningDay;
    }
}
