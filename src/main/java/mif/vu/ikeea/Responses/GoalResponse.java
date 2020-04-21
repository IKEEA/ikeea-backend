package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Enums.EGoalStatus;

import java.util.Date;

@Setter @Getter
public class GoalResponse {

    private Long id;
    private Date lastUpdated;
    private EGoalStatus status;
    private Long topicId;
    private String topicTitle;
    private Long userId;

    public GoalResponse(Goal goal) {
        this.id = goal.getId();
        this.lastUpdated = goal.getLastUpdate();
        this.status = goal.getStatus();
        this.topicId = goal.getTopic().getId();
        this.topicTitle = goal.getTopic().getTitle();
        this.userId = goal.getUser().getId();
    }
}
