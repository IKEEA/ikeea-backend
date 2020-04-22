package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Enums.EGoalStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GoalRequest {

    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private EGoalStatus status;

    @NotNull
    private Long topicId;

    @NotNull
    private Long userId;
}
