package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class UpdateTopicRequest {
    @Size(min = 3, max = 100)
    private String title;

    @Size(min = 3, max = 255)
    private String description;

    @Size(min = 1, max = 10)
    private Long parent;

    private List<Long> children;
}
