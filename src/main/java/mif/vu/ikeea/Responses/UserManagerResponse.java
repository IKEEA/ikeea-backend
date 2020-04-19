package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Enums.ERole;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public UserManagerResponse(ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.firstName = applicationUser.getFirstName();
        this.lastName = applicationUser.getLastName();
        this.email = applicationUser.getEmail();
        this.roles = applicationUser.getRoleNames();
        this.enabled = applicationUser.getEnabled();
        this.restrictionDays = applicationUser.getRestrictionDays();
    }
}
