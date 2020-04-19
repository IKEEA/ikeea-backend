package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.ApplicationUser;

@Getter
@Setter
public class UserManagerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer restrictionDays;

    public UserManagerResponse(ApplicationUser applicationUser){
        this.id = applicationUser.getId();
        this.firstName = applicationUser.getFirstName();
        this.lastName = applicationUser.getLastName();
        this.email = applicationUser.getEmail();
        this.restrictionDays = applicationUser.getRestrictionDays();
    }
}
