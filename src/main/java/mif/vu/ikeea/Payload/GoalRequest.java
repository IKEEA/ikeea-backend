package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Enums.EGoalStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class GoalRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private EGoalStatus status;

    @NotNull
    private Long topicId;

    @NotNull
    private Long userId;
}
