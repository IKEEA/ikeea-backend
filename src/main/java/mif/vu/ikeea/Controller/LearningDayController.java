package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Entity.Repository.TopicRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Manager.LearningDayManager;
import mif.vu.ikeea.Payload.LearningDayRequest;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.LearningDayResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/learning_day")
public class LearningDayController {

    @Autowired
    private LearningDayManager learningDayManager;

    @Autowired
    LearningDayRepository learningDayRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createLearningDay(@Valid @RequestBody LearningDayRequest learningDayRequest) {

        learningDayManager.create(learningDayRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Learning day added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody List<LearningDayResponse> list() {
        Iterable<LearningDay> learningDayIterable = learningDayRepository.findAll();
        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDayIterable) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody void delete(@PathVariable Long id) { learningDayRepository.deleteById(id); }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity updateLearningDay(@PathVariable Long id, @RequestBody LearningDayRequest learningDayRequest) {
        Optional<LearningDay> optionalLearningDay = learningDayRepository.findById(id);

        if (optionalLearningDay.isEmpty()) {
            throw new BadRequestHttpException("Learning day not found");
        }

        LearningDay learningDay = optionalLearningDay.get();
        learningDay.setDate(learningDayRequest.getDate());
        learningDayRepository.save(learningDay);

        return ResponseEntity.ok(new ApiResponse(true, "Learning day updated successfully"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody
    LearningDayResponse get(@PathVariable Long id){
        Optional<LearningDay> optionalLearningDay = learningDayRepository.findById(id);

        if (optionalLearningDay.isEmpty()) {
            throw new BadRequestHttpException("Empty Learning Day");
        }

        return new LearningDayResponse(optionalLearningDay.get());
    }
}
