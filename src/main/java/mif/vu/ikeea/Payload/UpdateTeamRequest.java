package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTeamRequest {

    @Size(min = 3, max = 15)
    private String title;

    private Long managerId;
}
