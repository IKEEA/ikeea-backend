package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Factory.LearningDayFactory;
import mif.vu.ikeea.Payload.LearningDayRequest;
import mif.vu.ikeea.RepositoryService.LearningDayService;
import mif.vu.ikeea.RepositoryService.TopicService;
import mif.vu.ikeea.RepositoryService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LearningDayManager {

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Transactional
    public LearningDay create(LearningDayRequest learningDayRequest) {
        ApplicationUser user = userService.loadById(learningDayRequest.getUserId());
        Topic topic = topicService.loadById(learningDayRequest.getTopicId());

        LearningDay learningDay = LearningDayFactory.createLearningDay(
                learningDayRequest.getTitle(),
                learningDayRequest.getDate(),
                topic,
                user
        );

        LearningDay result = learningDayService.add(learningDay);

        return result;
    }
}
