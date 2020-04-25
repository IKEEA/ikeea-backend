package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Manager.TeamManager;
import mif.vu.ikeea.RepositoryService.TeamService;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/team")
public class TeamController {

    @Autowired
    private UserService userService;

    @Autowired
    private TeamManager teamManager;

    @PostMapping(path = "/{managerId}/set-restriction-days")
    public @ResponseBody ResponseEntity setRestrictionDays(@PathVariable Long managerId, @RequestParam Integer restrictionDays) {
        ApplicationUser manager = userService.loadById(managerId);
        teamManager.updateRestrictionDay(manager.getChildren(), restrictionDays);

        return ResponseEntity.ok(new ApiResponse(true, "Team restriction days updated successfully"));
    }
}
