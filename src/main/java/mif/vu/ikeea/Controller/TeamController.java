package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Team;
import mif.vu.ikeea.Manager.TeamManager;
import mif.vu.ikeea.Payload.TeamRequest;
import mif.vu.ikeea.Payload.UpdateTeamRequest;
import mif.vu.ikeea.RepositoryService.TeamService;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.TeamResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/team")
public class TeamController {


    @Autowired
    TeamService teamService;

    @Autowired
    private TeamManager teamManager;

    @Autowired
    private UserService userService;


    @PostMapping("/add")
    public ResponseEntity<?> createTeam(@Valid @RequestBody TeamRequest teamRequest) {
        teamManager.create(teamRequest);

        //TODO change manager role to Leader

        return ResponseEntity.ok(new ApiResponse(true, "Team added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    List<TeamResponse> list(){
        List<Team> teams = teamService.getAll();
        List<TeamResponse> teamResponses = new ArrayList<>();

        for (Team team : teams) {
            teamResponses.add(new TeamResponse(team));
        }

        return teamResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        teamService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Team deleted"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody
    TeamResponse get(@PathVariable Long id) {
        Team team = teamService.loadById(id);

        return new TeamResponse(team);
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateGoal(@PathVariable Long id, @Valid @RequestBody UpdateTeamRequest updateTeamRequest) {
        Team team = teamService.loadById(id);
        teamManager.update(team,updateTeamRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Team updated successfully"));
    }

    @GetMapping(path = "/{managerId}/team")
    public @ResponseBody TeamResponse getManagerTeam(@PathVariable Long managerId) {
        Team team = teamService.loadByManagerId(managerId);

        return new TeamResponse(team);
    }

    @PreAuthorize("hasRole('LEADER')")
    @PostMapping(path = "/{managerId}/set-restriction-days")
    public @ResponseBody ResponseEntity setRestrictionDays(@PathVariable Long managerId, @RequestParam Integer restrictionDays) {
        ApplicationUser manager = userService.loadById(managerId);
        teamManager.updateRestrictionDay(manager.getChildren(), restrictionDays);

        return ResponseEntity.ok(new ApiResponse(true, "Team restriction days updated successfully"));
    }
}

