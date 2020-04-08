package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Entity.User;
import mif.vu.ikeea.Exceptions.BadRequestHttpException;
import mif.vu.ikeea.Manager.LearningDayManager;
import mif.vu.ikeea.Payload.ApiResponse;
import mif.vu.ikeea.Payload.LearningDayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/add")
    public ResponseEntity<?> createLearningDay(@Valid @RequestBody LearningDayRequest learningDayRequest) {

        LearningDay learningDay = learningDayManager.create(learningDayRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Learning day added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<LearningDay> list(){
        return learningDayRepository.findAll();
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody void delete(@PathVariable Long id) {
        learningDayRepository.deleteById(id);
    }

    /*@PutMapping(path = "/{id}/update")
    public @ResponseBody LearningDay updateLearningDay(@PathVariable Long id, @RequestParam User user) {
        Optional<LearningDay> LearningDay = learningDayRepository.findById(id);

        if (LearningDay.isEmpty()) {
            throw new BadRequestHttpException("Learning day not found");
        }

        Optional<User> optionalUser = userRepository.findById(user.getId());
        User user1 = optionalUser.get();

        LearningDay learningDay = LearningDay.get();
        learningDay.setUser(user1);
        learningDayRepository.save(learningDay);

        return learningDay;
    }*/
}
