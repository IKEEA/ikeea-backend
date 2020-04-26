package mif.vu.ikeea.Controller;

import mif.vu.ikeea.Entity.Comment;
import mif.vu.ikeea.Manager.CommentManager;
import mif.vu.ikeea.Payload.CommentRequest;
import mif.vu.ikeea.Payload.UpdateCommentRequest;
import mif.vu.ikeea.RepositoryService.CommentService;
import mif.vu.ikeea.Responses.ApiResponse;
import mif.vu.ikeea.Responses.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequest commentRequest) {
        commentManager.create(commentRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Comment added successfully"));
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    List<CommentResponse> list() {
        List<Comment> comments = commentService.getAll();
        List<CommentResponse> commentResponses = new ArrayList<>();

        for (Comment comment : comments) {
            commentResponses.add(new CommentResponse(comment));
        }

        return commentResponses;
    }

    @DeleteMapping(path = "/{id}/delete")
    public @ResponseBody ResponseEntity delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.ok(new ApiResponse(true, "Comment deleted"));
    }

    @PutMapping(path = "/{id}/update")
    public @ResponseBody ResponseEntity update(@PathVariable Long id, @Valid @RequestBody UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentService.loadById(id);
        commentManager.update(comment, updateCommentRequest);

        return ResponseEntity.ok(new ApiResponse(true, "Comment updated successfully"));
    }

    @GetMapping(path = "/{id}/get")
    public @ResponseBody CommentResponse get(@PathVariable Long id){
        Comment comment = commentService.loadById(id);

        return new CommentResponse(comment);
    }
}
