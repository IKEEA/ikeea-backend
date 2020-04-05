package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class LearningDayRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @Size(min = 3, max = 15)
    private Date date;

    @NotBlank
    @Size(max = 40)
    @Email
    private Topic topic;

    @NotBlank
    @Size(min = 6, max = 20)
    private User user;
}
