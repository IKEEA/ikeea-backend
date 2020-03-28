package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Manager.TeamCreationManager;
import mif.vu.ikeea.Manager.UserCreationManager;
import mif.vu.ikeea.Service.TeamServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private TeamCreationManager teamCreationManager;

    @Autowired
    private UserCreationManager userCreationManager;


    @PostMapping(path = "/add")
    public @ResponseBody String add (@RequestParam String first_name, @RequestParam String last_name,  @RequestParam String role, @RequestParam String email, @RequestParam String password, @RequestParam long manager_id, @RequestParam long team_id){

        try {
            if(email != null && password != null){
                Team team = teamCreationManager.get(team_id);
                userCreationManager.create(email,first_name,last_name, role, password, manager_id, team);
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "Saved";
    }

    @GetMapping(path = "/getAll")
    public @ResponseBody List<User> getAll (){
        return userCreationManager.getAll();
    }

    @GetMapping(path = "/delete")
    public @ResponseBody String delete (@RequestParam Long id){
        try {
        userCreationManager.delete(id);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "User deleted";
    }


    @PostMapping(path = "/updateEmail")
    public @ResponseBody String updateEmail (@RequestParam Long id, String email){
        try {
            User user = userCreationManager.get(id);
            user.setEmail(email);
            userCreationManager.update(user);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return "User updated";
    }
}
