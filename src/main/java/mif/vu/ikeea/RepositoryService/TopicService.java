package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.TopicRepository;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Transactional
    public Topic add(Topic topic) {
        return topicRepository.save(topic);
    }

    @Transactional
    public void delete(Long id) {
        topicRepository.deleteById(id);
    }

    @Transactional
    public void update(Topic topic) {
        topicRepository.save(topic);
    }

    @Transactional
    public Topic loadById(Long id) throws ResourceNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Topic was not found")
                );

        return topic;
    }

    public List<Topic> getAll() {
        Iterable<Topic> topicIterable = topicRepository.findAll();
        List<Topic> topics = new ArrayList<>();
        topicIterable.forEach(topics::add);

        return topics;
    }

    public List<Topic> findByLearningDayId(LearningDay learningDay) {
        Iterable<Topic> topicIterable = topicRepository.findAllByLearningDays(learningDay);
        List<Topic> topics = new ArrayList<>();
        topicIterable.forEach(topics::add);

        return topics;
    }
}
