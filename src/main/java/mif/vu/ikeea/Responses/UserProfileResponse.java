package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Enums.ERole;

import java.util.*;

@Getter @Setter
public class UserProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private List<ERole> roles;
    private Long teamId;
    private String managerFirstName = null;
    private String managerLastName = null;
    private String managerEmail = null;
    private Integer restrictionDays;

    public UserProfileResponse(ApplicationUser user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.enabled = user.getEnabled();
        this.roles = user.getRoleNames();
        this.teamId = user.getTeam().getId();
        this.restrictionDays = user.getRestrictionDays();

        ApplicationUser manager = user.getManager();

        if (manager != null) {
            this.managerFirstName = manager.getFirstName();
            this.managerLastName = manager.getLastName();
            this.managerEmail = manager.getEmail();
        }
    }
}
