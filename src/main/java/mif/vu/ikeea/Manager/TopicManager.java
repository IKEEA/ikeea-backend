package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Factory.TopicFactory;
import mif.vu.ikeea.Payload.TopicRequest;
import mif.vu.ikeea.Payload.UpdateTopicRequest;
import mif.vu.ikeea.RepositoryService.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TopicManager {
    @Autowired
    private TopicService topicService;

    @Transactional
    public Topic create(TopicRequest topicRequest) {
        topicService.checkTopicTitle(topicRequest.getTitle());

        Topic parent = null;

        if (topicRequest.getParent() != null) {
            parent = topicService.loadById(topicRequest.getParent());
        }

        Topic topic = TopicFactory.create(topicRequest, parent);
        Topic result = topicService.add(topic);

        return result;
    }

    @Transactional
    public void update(Topic topic, UpdateTopicRequest updateTopicRequest) {
        if (updateTopicRequest.getTitle() != null) {
            topicService.checkTopicTitle(updateTopicRequest.getTitle());
            topic.setTitle(updateTopicRequest.getTitle());
        }

        if (updateTopicRequest.getDescription() != null) {
            topic.setDescription(updateTopicRequest.getDescription());
        }

        if (updateTopicRequest.getParent() != null) {
            Topic parent = topicService.loadById(updateTopicRequest.getParent());
            topic.setParent(parent);
        }

        topicService.update(topic);
    }
}
