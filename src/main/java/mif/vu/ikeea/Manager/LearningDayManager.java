package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Entity.Repository.TopicRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Factory.LearningDayFactory;
import mif.vu.ikeea.Payload.LearningDayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LearningDayManager {

    @Autowired
    private LearningDayRepository learningDayRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    public LearningDay create(LearningDayRequest learningDayRequest) {

        Optional<User> optionalUser = userRepository.findById(learningDayRequest.getUserId());
        if (optionalUser.isEmpty()) {
            throw new BadRequestHttpException("Empty User");
        }
        User user = optionalUser.get();

        Optional<Topic> optionalTopic = topicRepository.findById(learningDayRequest.getTopicId());
        if (optionalTopic.isEmpty()) {
            throw new BadRequestHttpException("Empty Topic");
        }
        Topic topic = optionalTopic.get();

        LearningDay learningDay = LearningDayFactory.createLearningDay(
                learningDayRequest.getTitle(),
                learningDayRequest.getDate(),
                topic,
                user
        );

        LearningDay result = learningDayRepository.save(learningDay);

        return result;
    }
}