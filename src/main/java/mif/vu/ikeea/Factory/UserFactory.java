package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.ApplicationUser;

import java.util.Collections;

public class UserFactory {
    public static ApplicationUser createUser(String email, Role role, String password, ApplicationUser manager, String token) {
        ApplicationUser user = new ApplicationUser();

        user.setEmail(email);
        user.setRoles(Collections.singleton(role));
        user.setPassword(password);
        user.setManagerWithChild(manager);
        user.setToken(token);
        user.setTeam(manager.getTeam());

        return user;
    }
}
