package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.Comment;
import mif.vu.ikeea.Entity.Repository.CommentRepository;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public Comment add(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }

    @Transactional
    public void update(Comment comment) {
        commentRepository.save(comment);
    }

    @Transactional
    public Comment loadById(Long id) throws ResourceNotFoundException {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Comment was not found")
                );

        return comment;
    }

    public List<Comment> getAll() {
        Iterable<Comment> commentIterable = commentRepository.findAll();
        List<Comment> comments = new ArrayList<>();
        commentIterable.forEach(comments::add);

        return comments;
    }
}
