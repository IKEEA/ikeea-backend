package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Topic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class LearningDayResponse {
    private Long id;
    private Date date;
    private String title;
    private List<TopicResponse> topicResponses;
    private Long userId;

    public LearningDayResponse(LearningDay learningDay) {
        this.id = learningDay.getId();
        this.date = learningDay.getDate();
        this.title = learningDay.getTitle();
        this.userId = learningDay.getUser().getId();
        this.topicResponses = getTopicResponses(learningDay.getTopics());
    }

    public List<TopicResponse> getTopicResponses(List<Topic> topics){
        List<TopicResponse> topicResponses = new ArrayList<>();

        for (Topic topic : topics) {
            topicResponses.add(new TopicResponse(topic));
        }

        return topicResponses;
    }

}
