package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Factory.LearningDayFactory;
import mif.vu.ikeea.Payload.LearningDayRequest;
import mif.vu.ikeea.Payload.UpdateLearningDayRequest;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import mif.vu.ikeea.RepositoryService.LearningDayService;
import mif.vu.ikeea.RepositoryService.TopicService;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.LearningDayResponse;
import mif.vu.ikeea.Responses.UserProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class LearningDayManager {

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Transactional
    public List<LearningDay> create(LearningDayRequest learningDayRequest) {
        ApplicationUser user = userService.loadById(learningDayRequest.getUserId());
        List<Long> topicIds = learningDayRequest.getTopicIds();
        List<LearningDay> learningDayList = new ArrayList<>();

        Topic topic = topicService.loadById(topicIds.get(0));

        LearningDay learningDay = LearningDayFactory.createLearningDay(
                learningDayRequest.getTitle(),
                learningDayRequest.getDate(),
                topic,
                user
        );

        LearningDay result = learningDayService.add(learningDay);
        learningDayList.add(result);

        learningDayList = learningDayService.addTopics(topicIds, learningDay, learningDayList);

        return learningDayList;
    }

    @Transactional
    public LearningDayResponse update(LearningDay learningDay, UpdateLearningDayRequest updateLearningDayRequest) {

        if (updateLearningDayRequest.getTitle() != null) {
            learningDay.setTitle(updateLearningDayRequest.getTitle());
        }

        if (updateLearningDayRequest.getDate() != null) {
            learningDay.setDate(updateLearningDayRequest.getDate());
        }

        if (updateLearningDayRequest.getTopicIds() != null) {
            learningDayService.updateTopics(updateLearningDayRequest.getTopicIds(), learningDay);
        }

        if (updateLearningDayRequest.getUserId() != null) {
            ApplicationUser user = userService.loadById(updateLearningDayRequest.getUserId());
            learningDay.setUser(user);
        }

        learningDayService.update(learningDay);

        return new LearningDayResponse(learningDay);
    }
}
