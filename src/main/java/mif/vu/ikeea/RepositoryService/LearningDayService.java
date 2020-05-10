package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LearningDayService {
    @Autowired
    private LearningDayRepository learningDayRepository;

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Transactional
    public LearningDay add(LearningDay learningDay) {
        return learningDayRepository.save(learningDay);
    }

    @Transactional
    public void delete(Long id) {
        LearningDay learningDay = learningDayService.loadById(id);

        ApplicationUser user = learningDay.getUser();
        user.incrementRestrictionDays();
        userService.update(user);

        deleteTopics(learningDay);
        learningDayRepository.deleteById(id);
    }

    @Transactional
    public void update(LearningDay learningDay) {
        learningDayRepository.save(learningDay);
    }

    @Transactional
    public void addTopics(List<Long> topicIds, LearningDay learningDay) {

        for (Long topicId : topicIds) {
            Topic additionalTopic = topicService.loadById(topicId);

            learningDay.getTopics().add(additionalTopic);
            additionalTopic.getLearningDays().add(learningDay);

            learningDayService.add(learningDay);
        }
    }

    @Transactional
    public void updateTopics(List<Long> topicIds, LearningDay learningDay) {
        deleteTopics(learningDay);

        for (Long topicId : topicIds) {
            Topic additionalTopic = topicService.loadById(topicId);

            learningDay.getTopics().add(additionalTopic);
            additionalTopic.getLearningDays().add(learningDay);

            learningDayService.add(learningDay);
        }
    }

    @Transactional
    public void deleteTopics(LearningDay learningDay) {
        List<Topic> topics = topicService.findByLearningDayId(learningDay);

        for (Topic topic : topics) {
            learningDay.getTopics().remove(topic);
            topic.getLearningDays().remove(learningDay);

            learningDayService.add(learningDay);
            topicService.update(topic);
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
