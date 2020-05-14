package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterGoalRequest {

    private Long topicId;

    private Long userId;

    private Integer page;

    private Integer size;
}
