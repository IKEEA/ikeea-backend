package mif.vu.ikeea.Factory;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;

public class UserFactory {

    public static User createUser(String email, String first_name, String last_name, String password, Team team){
        User user = new User();
        user.setEmail(email);
        user.setFirstName(first_name);
        user.setLastName(last_name);
        user.setPassword(password);
        user.setTeam(team);
        return user;
    }
}
