package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.ApplicationUser;

import java.util.Collections;

public class UserFactory {
    public static ApplicationUser createUser(String email, Role role, String password, ApplicationUser manager, Team team, String token) {
        ApplicationUser user = new ApplicationUser();

        user.setEmail(email);
        user.setRoles(Collections.singleton(role));
        user.setPassword(password);
        user.setManager(manager);
        user.setToken(token);
        user.setTeam(team);

        return user;
    }
}
