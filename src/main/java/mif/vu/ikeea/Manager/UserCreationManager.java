package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Enums.Role;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreationManager
{
    @Autowired
    private UserServiceInterface userServiceInterface;

    public User create(String email, String first_name, String last_name, String role, String password, long manager_id, Team team){
        Role userRole = Role.valueOf(role);
        User manager = userServiceInterface.findOneById(manager_id);
        User user = UserFactory.createUser(email, first_name, last_name, userRole, password, manager, team);
        userServiceInterface.add(user);

        return user;
    }
}