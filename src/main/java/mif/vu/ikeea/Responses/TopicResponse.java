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
    private Long parentId;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.description = topic.getDescription();
        this.parentId = ((topic.getParent() == null) ? 0 : topic.getParent().getId());
    }

    private List<TopicResponse> getChildrenList(List<Topic> topics) {
        List<TopicResponse> topicResponses = new ArrayList<>();

        for (Topic topic : topics) {
            topicResponses.add(new TopicResponse(topic));
        }

        return topicResponses;
    }
}
