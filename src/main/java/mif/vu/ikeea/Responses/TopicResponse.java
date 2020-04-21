package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.RepositoryService.TopicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TopicResponse {
    private Long id;
    private String title;
    private String description;
    private List<TopicResponse> topicResponses;

    @Autowired
    private TopicService topicService;

    public TopicResponse(Topic topic) {
        this.id = topic.getId();
        this.title = topic.getTitle();
        this.description = topic.getDescription();
        this.topicResponses = getTopicChildrenList(topic.getChildrenList());
    }

    private List<TopicResponse> getTopicChildrenList(List<Topic> topics){
        List<TopicResponse> topicResponses = new ArrayList<>();

        for (Topic topic : topics) {
            topicResponses.add(new TopicResponse(topic));
        }

        return topicResponses;
    }
}
