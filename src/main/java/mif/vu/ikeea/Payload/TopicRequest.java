package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TopicRequest {
    @NotBlank
    private String title;

    @Size(min = 3, max = 255)
    private String description;

    private Long parent;
}
