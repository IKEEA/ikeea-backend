package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTopicRequest {
    @Size(min = 3, max = 100)
    private String title;

    @Size(min = 3, max = 255)
    private String description;

    private Long parent;
}
