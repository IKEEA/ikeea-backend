package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class VerifyUserRequest {
    @NotBlank
    @Size(min = 3, max = 15)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 15)
    private String lastName;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
