package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.RepositoryService.LearningDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class LearningDayFactory {

    @Autowired
    LearningDayService learningDayService;

    public LearningDay createLearningDay(String title, Date date, ApplicationUser user, List<Long> topicIds) {
        LearningDay learningDay = new LearningDay();

        learningDay.setTitle(title);
        learningDay.setDate(date);
        learningDay.setUser(user);
        learningDayService.addTopics(topicIds, learningDay);

        return learningDay;
    }
}
