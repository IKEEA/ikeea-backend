package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Role;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.ERole;

import java.util.Collections;

public class UserFactory {

    public static User createUser(String email, String firstName, String lastName, Role role, String password, User manager, Team team){
        User user = new User();

        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRoles(Collections.singleton(role));
        user.setPassword(password);
        user.setManager(manager);
        user.setTeam(team);

        return user;
    }
}
