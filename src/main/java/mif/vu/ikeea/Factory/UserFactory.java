package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.ERole;

import java.util.Collections;

public class UserFactory {

    public static User createUser(String email, String first_name, String last_name, ERole role, String password, User manager, Team team){
        User user = new User();

        user.setEmail(email);
        user.setFirstName(first_name);
        user.setLastName(last_name);
//        user.setRoles(Collections.singleton(userRole));
        user.setPassword(password);
        user.setManager(manager);
        user.setTeam(team);

        return user;
    }
}
