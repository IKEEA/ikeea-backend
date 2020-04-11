package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.ApplicationUser;

import java.util.*;

@Getter @Setter
public class UserProfileResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private List<String> roles;
    private Long teamId;
    private String managerFirstName;
    private String managerLastName;
    private Integer learningDays;

    public UserProfileResponse(ApplicationUser user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.enabled = user.getEnabled();
        this.roles = buildRolesName(user.getRoles());
        this.teamId = user.getTeam().getId();

        ApplicationUser manager = user.getManager();
        this.managerFirstName = manager != null ? manager.getFirstName() : null;
        this.managerLastName = manager != null ? manager.getLastName() : null;
        //TODO learning days
    }

    private List<String> buildRolesName(Set<Role> roles) {
        List<String> roleNames = new ArrayList<>();

        for (Role role : roles) {
            roleNames.add(role.getName().toString());
        }

        return roleNames;
    }
}
