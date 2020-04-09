package mif.vu.ikeea.Responses;

import mif.vu.ikeea.Entity.LearningDay;

import java.util.Date;

public class LearningDayResponse {

    private Long id;
    private Date date;
    private String title;
    private Long topicId;
    private Long userId;

    public LearningDayResponse(LearningDay learningDay){
        this.id = learningDay.getId();
        this.date = learningDay.getDate();
        this.title = learningDay.getTitle();
        this.topicId = learningDay.getTopic().getId();
        this.userId = learningDay.getUser().getId();
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public Long getTopicId() { return topicId; }

    public void setTopicId(Long topicId) { this.topicId = topicId; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

}
