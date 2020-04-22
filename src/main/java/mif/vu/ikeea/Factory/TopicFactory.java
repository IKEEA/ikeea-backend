package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Payload.TopicRequest;

public class TopicFactory {
    public static Topic create (TopicRequest topicRequest, Topic parent) {
        Topic topic = new Topic();

        topic.setTitle(topicRequest.getTitle());
        topic.setDescription(topicRequest.getDescription());

        if (parent != null) {
            topic.setParent(parent);
        }

        return topic;
    }
}
