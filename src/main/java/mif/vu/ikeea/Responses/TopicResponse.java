package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;

@Getter
@Setter
public class TopicResponse {

    private long id;
    public TopicResponse(Topic topic){
        this.id = topic.getId();
    }
}
