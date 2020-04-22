package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Enums.ERole;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserManagerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer restrictionDays;
    private Boolean enabled;
    private List<ERole> roles;
    private List<UserManagerResponse> children = new ArrayList<>();
}
