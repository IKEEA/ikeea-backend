package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentRequest {

    @NotBlank
    @Size(min = 1, max = 1000)
    private String comment;

    @NotNull
    private Long learningDayId;

    @NotNull
    private Long userId;
}
