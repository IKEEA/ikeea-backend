package mif.vu.ikeea.Payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Entity.User;
import org.springframework.format.annotation.DateTimeFormat;

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

    @NotBlank
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date date;

    @NotBlank
    @Size(max = 4000)
    @Email
    private Topic topic;

    @NotBlank
    private User user;
}
