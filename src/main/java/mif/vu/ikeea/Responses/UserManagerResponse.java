package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Role;

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
    private List<String> roles;

    public UserManagerResponse(ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.firstName = applicationUser.getFirstName();
        this.lastName = applicationUser.getLastName();
        this.email = applicationUser.getEmail();
        this.roles = buildRolesName(applicationUser.getRoles());
        this.enabled = applicationUser.getEnabled();
        this.restrictionDays = applicationUser.getRestrictionDays();
    }

    private List<String> buildRolesName(Set<Role> roles) {
        List<String> roleNames = new ArrayList<>();

        for (Role role : roles) {
            roleNames.add(role.getName().toString());
        }

        return roleNames;
    }


}
