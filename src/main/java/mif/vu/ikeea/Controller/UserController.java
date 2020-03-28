package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Factory.UserFactory;
import mif.vu.ikeea.Manager.TeamCreationManager;
import mif.vu.ikeea.Manager.UserCreationManager;
import mif.vu.ikeea.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private UserCreationManager userCreationManager;


    @PostMapping(path = "/add")
    public @ResponseBody String add (@RequestParam String name, @RequestParam String email, @RequestParam String pssw, @RequestParam long team_id){
        TeamCreationManager teamCreationManager = new TeamCreationManager(teamService);
        Team team = teamCreationManager.getTeam(team_id);
        userCreationManager.createUser(email,name,name,pssw,team);
        return "Saved";
    }

/*
    @GetMapping(path = "/get")
    public @ResponseBody List<User> getAllUsers (){
        return userService.getAllUsers();
    }


    @GetMapping(path = "/delete")
    public @ResponseBody String deleteUser (@RequestParam Long id){
        userService.deleteUserById(id);
        return "User deleted";
    }*/

}
