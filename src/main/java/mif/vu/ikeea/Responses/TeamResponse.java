package mif.vu.ikeea.Responses;

import lombok.Getter;
import lombok.Setter;
import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Team;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class TeamResponse {
    private Long id;
    private String title;
    private List<UserProfileResponse> teamMembers;
    private Long managerId;

    public TeamResponse(Team team) {
        this.id = team.getId();
        this.title = team.getTitle();
        this.managerId = team.getManager().getId();
        this.teamMembers = getMembers(team.getUsers());
    }

    private List<UserProfileResponse> getMembers(List<ApplicationUser> applicationUsers){
        List<UserProfileResponse> userProfileResponses = new ArrayList<>();
        for (ApplicationUser applicationUser : applicationUsers) {
            userProfileResponses.add(new UserProfileResponse(applicationUser));
        }

        return userProfileResponses;
    }
}
