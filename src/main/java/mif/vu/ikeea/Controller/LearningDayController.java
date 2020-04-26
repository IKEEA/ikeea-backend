package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Manager.LearningDayManager;
import mif.vu.ikeea.Payload.LearningDayRequest;
import mif.vu.ikeea.Payload.UpdateLearningDayRequest;
import mif.vu.ikeea.RepositoryService.LearningDayService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.LearningDayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/learning-day")
public class LearningDayController {

    @Autowired
    private LearningDayManager learningDayManager;

    @Autowired
    private LearningDayService learningDayService;

    @PostMapping("/add")
    public ResponseEntity<?> createLearningDay(@Valid @RequestBody LearningDayRequest learningDayRequest) {
        learningDayManager.create(learningDayRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Learning day added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody List<LearningDayResponse> list() {
        List<LearningDay> learningDays = learningDayService.getAll();
        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDays) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }

    @GetMapping(path = "/{userId}/list")
    public @ResponseBody List<LearningDayResponse> getUserLearningDaysList(@PathVariable Long userId) {
        List<LearningDay> learningDays = learningDayService.getAllByUserId(userId);
        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDays) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody void delete(@PathVariable Long id) { learningDayService.delete(id); }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateLearningDay(@PathVariable Long id, @RequestBody UpdateLearningDayRequest updateLearningDayRequest) {
        LearningDay learningDay = learningDayService.loadById(id);
        learningDayManager.update(learningDay, updateLearningDayRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Learning day updated successfully"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody
    LearningDayResponse get(@PathVariable Long id){
        LearningDay learningDay = learningDayService.loadById(id);

        return new LearningDayResponse(learningDay);
    }
}
