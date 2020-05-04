package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Manager.GoalManager;
import mif.vu.ikeea.Payload.GoalRequest;
import mif.vu.ikeea.Payload.UpdateGoalRequest;
import mif.vu.ikeea.RepositoryService.GoalService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.GoalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/goal")
public class GoalController {

    @Autowired
    private GoalManager goalManager;

    @Autowired
    private GoalService goalService;

    @PostMapping("/add")
    public ResponseEntity<?> createGoal(@Valid @RequestBody GoalRequest goalRequest) {
        goalManager.create(goalRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Goal added successfully"));
    }

    @GetMapping(path = "/{managerId}/team-list")
    public @ResponseBody List<GoalResponse> list(@PathVariable Long managerId)  {
        List<Goal> goals = goalService.getAllByManagerId(managerId);
        List<GoalResponse> goalResponses = new ArrayList<>();

        for (Goal goal : goals) {
            goalResponses.add(new GoalResponse(goal));
        }

        return goalResponses;
    }
    
    @GetMapping(path = "/{userId}/list")
    public @ResponseBody List<GoalResponse> getUserLearningDaysList(@PathVariable Long userId) {
        List<Goal> goals = goalService.getAllByUserId(userId);
        List<GoalResponse> goalResponses = new ArrayList<>();

        for (Goal goal : goals) {
            goalResponses.add(new GoalResponse(goal));
        }

        return goalResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        goalService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Goal deleted"));
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateGoal(@PathVariable Long id, @Valid @RequestBody UpdateGoalRequest updateGoalRequest) {
        Goal goal = goalService.loadById(id);
        goalManager.update(goal, updateGoalRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Goal updated successfully"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody GoalResponse get(@PathVariable Long id){
        Goal goal = goalService.loadById(id);

        return new GoalResponse(goal);
    }
}
