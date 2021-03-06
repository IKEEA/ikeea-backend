package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Responses.UserManagerResponse;

import java.util.List;

public class UserManagerResponseFactory {
    public static UserManagerResponse create(ApplicationUser user, List<ApplicationUser> childUsers, List<UserManagerResponse> responses) {
        UserManagerResponse response = new UserManagerResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRoles(user.getRoleNames());
        response.setEnabled(user.getEnabled());
        response.setRestrictionDays(user.getRestrictionDays());

        for (ApplicationUser childrenUser : childUsers) {
            responses.add(create(childrenUser, childrenUser.getChildren(), responses));
        }

        return response;
    }
}
