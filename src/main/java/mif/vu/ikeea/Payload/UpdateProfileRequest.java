package mif.vu.ikeea.Payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter @Setter
public class UpdateProfileRequest{

    @Size(min = 3, max = 15)
    private String firstName;

    @Size(min = 3, max = 15)
    private String lastName;

    @Size(max = 40)
    @Email
    private String email;

    @Size(min = 6, max = 20)
    private String password;

    @Size(min = 6, max = 20)
    private String oldPassword;
}
