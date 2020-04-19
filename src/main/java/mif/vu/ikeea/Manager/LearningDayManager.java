package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Entity.Repository.TopicRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.LearningDayFactory;
import mif.vu.ikeea.Payload.LearningDayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class LearningDayManager {

    @Autowired
    private LearningDayRepository learningDayRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @Transactional
    public LearningDay create(LearningDayRequest learningDayRequest) {

        Optional<ApplicationUser> optionalUser = userRepository.findById(learningDayRequest.getUserId());
        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Empty User");
        }

        Optional<Topic> optionalTopic = topicRepository.findById(learningDayRequest.getTopicId());

        if (optionalTopic.isEmpty()) {
            throw new BadRequestHttpException("Empty Topic");
        }

        LearningDay learningDay = LearningDayFactory.createLearningDay(
                learningDayRequest.getTitle(),
                learningDayRequest.getDate(),
                optionalTopic.get(),
                optionalUser.get()
        );

        LearningDay result = learningDayRepository.save(learningDay);

        return result;
    }
}
