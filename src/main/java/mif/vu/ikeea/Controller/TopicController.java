package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Topic;
import mif.vu.ikeea.Manager.TopicManager;
import mif.vu.ikeea.Payload.TopicRequest;
import mif.vu.ikeea.Payload.UpdateTopicRequest;
import mif.vu.ikeea.RepositoryService.TopicService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.TopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OptimisticLockException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/topic")
public class TopicController {

    @Autowired
    private TopicManager topicManager;

    @Autowired
    private TopicService topicService;

    @PostMapping("/add")
    public ResponseEntity<?> createTopic(@Valid @RequestBody TopicRequest topicRequest) {
        topicManager.create(topicRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Topic added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody List<TopicResponse> list() {
        List<Topic> topics = topicService.getAll();
        List<TopicResponse> topicResponses = new ArrayList<>();

        for (Topic topic : topics) {
            topicResponses.add(new TopicResponse(topic));
        }

        return topicResponses;
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        topicService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Topic deleted"));
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity update(@PathVariable Long id, @Valid @RequestBody UpdateTopicRequest updateTopicRequest) {
        Topic topic = topicService.loadById(id);

        try {
            topicManager.update(topic, updateTopicRequest);
        } catch (OptimisticLockException ex) {
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse(false, "Topic was modified by other user"));
        }

        return ResponseEntity.ok(new ApiResponse(true, "Topic updated successfully"));
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody TopicResponse get(@PathVariable Long id){
        Topic topic = topicService.loadById(id);

        return new TopicResponse(topic);
    }
}
