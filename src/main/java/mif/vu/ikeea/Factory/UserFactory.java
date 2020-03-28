package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.Role;

public class UserFactory {

    public static User createUser(String email, String first_name, String last_name, Role role, String password, User manager, Team team){
        User user = new User();
        user.setEmail(email);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setRole(role);
        user.setPassword(password);
        user.setManager(manager);
        user.setTeam(team);
        return user;
    }
}
