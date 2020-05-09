package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.LearningDay;
import mif.vu.ikeea.Entity.Repository.LearningDayRepository;
import mif.vu.ikeea.Manager.LearningDayManager;
import mif.vu.ikeea.Payload.LearningDayRequest;
import mif.vu.ikeea.Payload.UpdateLearningDayRequest;
import mif.vu.ikeea.RepositoryService.LearningDayService;
import mif.vu.ikeea.RepositoryService.UserService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.LearningDayResponse;
import mif.vu.ikeea.Specifications.LearningDaySpecification;
import mif.vu.ikeea.Specifications.LearningDaySpecificationsBuilder;
import mif.vu.ikeea.Specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping(path="/api/learning-day")
public class LearningDayController {

    @Autowired
    private LearningDayManager learningDayManager;

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private UserService userService;

    @Autowired
    private LearningDayRepository learningDayRepository;

    @PostMapping("/add")
    public ResponseEntity<?> createLearningDay(@Valid @RequestBody LearningDayRequest learningDayRequest) {
        learningDayManager.create(learningDayRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Learning day added successfully"));
    }
/*
 //   @GetMapping(path = "/{managerId}/list")
 //   public @ResponseBody List<LearningDayResponse> getLearningDaysList(@PathVariable Long managerId, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd")Date date) {
    @GetMapping(path = "/{managerId}/list")
    public @ResponseBody List<LearningDayResponse> getLearningDaysList(@PathVariable Long managerId, @RequestParam(required = false) Long userId, @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestParam(required = false) Long topicId) {
        List<LearningDay> learningDays = new ArrayList<>();

        if(userId != null){
            learningDays = learningDayService.getAllByManagerIdAndUserId(managerId, userId);
        }
        if (date != null){
            learningDays = learningDayService.getAllByManagerIdAndDate(managerId, date);
        }
        if (topicId != null) {
            learningDays = learningDayService.getAllByUserIdAndTopicId(managerId, topicId);
        }
        else {
            learningDays = learningDayService.getAllByManagerId(managerId);
        }

        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDays) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }
*/

    @GetMapping(path = "/{managerId}/listParameters")
    public List<LearningDayResponse> getLearningDaysList(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)Date date, @RequestParam(required = false) Long userId, @RequestParam(required = false) Long topicId) {

        //LearningDaySpecification spec = new LearningDaySpecification(new SearchCriteria("date", ":", date));
        LearningDaySpecification spec = new LearningDaySpecification(new SearchCriteria("user", ":", userId));
        //LearningDaySpecification spec = new LearningDaySpecification(new SearchCriteria("topic", ":", topicId));

        List<LearningDay> learningDays = learningDayRepository.findAll(spec);

        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDays) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }

    @GetMapping(path = "/{managerId}/listSearch")
    public List<LearningDayResponse> getLearningDaysList(
            @RequestParam(value = "search") String search) {

        LearningDaySpecificationsBuilder builder = new LearningDaySpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }

        Specification<LearningDay> spec = builder.build();
        List<LearningDay> learningDays = learningDayRepository.findAll(spec);

        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDays) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }

    @GetMapping(path = "/{userId}/user-list")
    public @ResponseBody List<LearningDayResponse> getUserLearningDaysList(@PathVariable Long userId) {
        List<LearningDay> learningDays = learningDayService.getAllByUserId(userId);
        List<LearningDayResponse> learningDayResponses = new ArrayList<>();

        for (LearningDay learningDay : learningDays) {
            learningDayResponses.add(new LearningDayResponse(learningDay));
        }

        return learningDayResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        learningDayService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Learning day deleted successfully"));
    }

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
