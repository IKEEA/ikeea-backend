package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LearningDayService {
    @Autowired
    private LearningDayRepository learningDayRepository;

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private TopicService topicService;

    @Transactional
    public LearningDay add(LearningDay learningDay) {
        return learningDayRepository.save(learningDay);
    }

    @Transactional
    public void delete(Long id) {
        learningDayRepository.deleteById(id);
    }

    @Transactional
    public void update(LearningDay learningDay) {
        learningDayRepository.save(learningDay);
    }

    @Transactional
    public List<LearningDay> addTopics(List<Long> topicIds, LearningDay learningDay, List<LearningDay> learningDayList) {

        topicIds.remove(0);
        if(topicIds != null) {
            for (Long topicId : topicIds) {
                Topic additionalTopic = topicService.loadById(topicId);

                learningDay.getTopics().add(additionalTopic);
                additionalTopic.getLearningDays().add(learningDay);

                LearningDay additionalResult = learningDayService.add(learningDay);
                learningDayList.add(additionalResult);
            }
        }

        return learningDayList;
    }

    @Transactional
    public void updateTopics(List<Long> topicIds, LearningDay learningDay) {

        for (Long topicId : topicIds) {
            Topic additionalTopic = topicService.loadById(topicId);

            learningDay.getTopics().add(additionalTopic);
            additionalTopic.getLearningDays().add(learningDay);

            learningDayService.add(learningDay);
        }
    }

    @Transactional
    public LearningDay loadById(Long id) throws ResourceNotFoundException {
        LearningDay learningDay = learningDayRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Learning day was not found")
                );

        return learningDay;
    }

    public List<LearningDay> getAll() {
        Iterable<LearningDay> learningDayIterable = learningDayRepository.findAll();
        List<LearningDay> learningDays = new ArrayList<>();
        learningDayIterable.forEach(learningDays::add);

        return learningDays;
    }

    public List<LearningDay> getAllByUserId(Long userId) {
        Iterable<LearningDay> learningDayIterable = learningDayRepository.findAllByUserId(userId);
        List<LearningDay> learningDays = new ArrayList<>();
        learningDayIterable.forEach(learningDays::add);

        return learningDays;
    }
}
