package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.RepositoryService.TeamService;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.UserManagerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/manager")
public class ManagerController {

    @Autowired
    UserService userService;

    @Autowired
    TeamService teamService;

    @GetMapping(path = "/{id}/users")
    public @ResponseBody
    List<UserManagerResponse> list(@PathVariable Long id) {

        //TODO add check if this user has manager role

        ApplicationUser manager = userService.loadById(id);
        Team team = teamService.getTeamByManager(manager.getId());

        List<ApplicationUser> users = team.getUsers();
        List<UserManagerResponse> userManagerResponses = new ArrayList<>();

        for (ApplicationUser applicationUser : users) {
            userManagerResponses.add(new UserManagerResponse(applicationUser));
        }

        return userManagerResponses;
    }
}
