package mif.vu.ikeea.Controller;

import jdk.jfr.DataAmount;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Manager.GoalManager;
import mif.vu.ikeea.Payload.FilterGoalRequest;
import mif.vu.ikeea.Payload.GoalRequest;
import mif.vu.ikeea.Payload.UpdateGoalRequest;
import mif.vu.ikeea.RepositoryService.GoalService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.GoalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
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

    @PostMapping(path = "/{managerId}/team-list")
    public @ResponseBody List<GoalResponse> list(@PathVariable Long managerId, @RequestBody FilterGoalRequest filterGoalRequest)  {
        List<Goal> goals = goalService.getAll(managerId, filterGoalRequest);

        return goalListToResponse(goals);
    }
    
    @GetMapping(path = "/{userId}/list")
    public @ResponseBody List<GoalResponse> getUserLearningDaysList(@PathVariable Long userId) {
        List<Goal> goals = goalService.getAllByUserId(userId);

        return goalListToResponse(goals);
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        goalService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Goal deleted"));
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateGoal(@PathVariable Long id, @Valid @RequestBody UpdateGoalRequest updateGoalRequest) {
        Goal goal = goalService.loadById(id);

        try {
            goalManager.update(goal, updateGoalRequest);
        } catch (OptimisticLockException ex) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(false, "Goal was modified"));
        }

        return ResponseEntity.ok(new ApiResponse(true, "Goal updated successfully"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody GoalResponse get(@PathVariable Long id){
        Goal goal = goalService.loadById(id);

        return new GoalResponse(goal);
    }

    private List<GoalResponse> goalListToResponse(List<Goal> goals){
        List<GoalResponse> goalResponses = new ArrayList<>();

        for (Goal goal : goals) {
            goalResponses.add(new GoalResponse(goal));
        }

        return goalResponses;
    }
}
