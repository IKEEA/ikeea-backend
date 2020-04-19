package mif.vu.ikeea.Payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class LearningDayRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    @NotNull
    private Long topicId;

    @NotNull
    private Long userId;

}
