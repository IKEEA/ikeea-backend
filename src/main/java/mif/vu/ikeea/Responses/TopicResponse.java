package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Topic;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TopicResponse {
    private Long id;
    private String title;
    private String description;
    private List<TopicResponse> children;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.description = topic.getDescription();
        this.children = getChildrenList(topic.getChildren());
    }

    private List<TopicResponse> getChildrenList(List<Topic> topics) {
        List<TopicResponse> topicResponses = new ArrayList<>();

        for (Topic topic : topics) {
            topicResponses.add(new TopicResponse(topic));
        }

        return topicResponses;
    }
}
