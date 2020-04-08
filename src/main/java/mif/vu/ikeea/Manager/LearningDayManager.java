package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Factory.LearningDayFactory;
import mif.vu.ikeea.Payload.LearningDayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LearningDayManager {

    @Autowired
    private LearningDayRepository learningDayRepository;

    public LearningDay create(LearningDayRequest learningDayRequest) {

        LearningDay learningDay = LearningDayFactory.createLearningDay(
                learningDayRequest.getTitle(),
                learningDayRequest.getDate(),
                learningDayRequest.getTopic(),
                learningDayRequest.getUser()
        );

        LearningDay result = learningDayRepository.save(learningDay);

        return result;
    }
}
