package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Repository.GoalRepository;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Manager.GoalManager;
import mif.vu.ikeea.Payload.GoalRequest;
import mif.vu.ikeea.Payload.UpdateGoalRequest;
import mif.vu.ikeea.Payload.UpdateProfileRequest;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.GoalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/goal")
public class GoalController {

    @Autowired
    private GoalManager goalManager;

    @Autowired
    private GoalRepository goalRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createGoal(@Valid @RequestBody GoalRequest goalRequest) {

        goalManager.create(goalRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Goal added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody List<GoalResponse> list() {
        Iterable<Goal> goalIterable = goalRepository.findAll();
        List<GoalResponse> goalResponses = new ArrayList<>();

        for (Goal goal : goalIterable) {
            goalResponses.add(new GoalResponse(goal));
        }

        return goalResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        goalRepository.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Goal deleted"));
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateGoal(@PathVariable Long id, @Valid @RequestBody UpdateGoalRequest updateGoalRequest) {
        Optional<Goal> optionalGoal = goalRepository.findById(id);

        if (optionalGoal.isEmpty()) {
            throw new BadRequestHttpException("Goal not found");
        }

        Goal goal = optionalGoal.get();

        goalManager.update(goal, updateGoalRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Goal updated successfully"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody
    GoalResponse get(@PathVariable Long id){
        Optional<Goal> optionalGoal = goalRepository.findById(id);

        if (optionalGoal.isEmpty()) {
            throw new BadRequestHttpException("Goal is empty");
        }

        return new GoalResponse(optionalGoal.get());
    }
}
