package mif.vu.ikeea.Manager;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Service.TeamService;
import mif.vu.ikeea.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreationManager
{
    @Autowired
    private UserService userService;

    public void createUser(String email, String first_name, String last_name, String password, Team team){
        User user = UserFactory.createUser(email,first_name,last_name,password,team);
        userService.addUser(user);
    }

    public User getUser(long id){
        return userService.findUserById(id);
    }

}
