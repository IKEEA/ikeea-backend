package mif.vu.ikeea.Payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Entity.User;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class LearningDayRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String title;

    //Reikia patikrinti
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date date;

    private Long topic_id;

    private Long user_id;

    @Size(min = 0, max = 1000)
    private String comment;
}
