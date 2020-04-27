package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class TeamRequest {
    @NotBlank
    @Size(min = 3, max = 15)
    private String title;

    @Size(min = 3, max = 100)
    private String description;

    @NotNull
    private Long managerId;
}
