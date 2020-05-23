package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.*;
import mif.vu.ikeea.Entity.Repository.UserRepository;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import mif.vu.ikeea.Exceptions.UserDeleteException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private LearningDayService learningDayService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private CommentService commentService;

    @Transactional
    public ApplicationUser add(ApplicationUser user) {
        return userRepository.save(user);
    }

    @Transactional
    public void delete(Long id) {
        deleteUserData(id);
        userRepository.deleteById(id);
    }

    @Transactional
    public void update(ApplicationUser user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmailAndEnabled(email, true)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + email)
                );

        return new User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    @Transactional
    public ApplicationUser loadByEmail(String email) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email : " + email)
                );

        return user;
    }

    @Transactional
    public ApplicationUser findByEmail(String email) throws ResourceNotFoundException {
        ApplicationUser user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with username or email : " + email)
                );

        return user;
    }

    @Transactional
    public Boolean existsByEmail(String email) throws UsernameNotFoundException {
        return userRepository.existsByEmail(email);
    }

    @Transactional
    public ApplicationUser loadById(Long id) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with")
                );

        return user;
    }

    @Transactional
    public ApplicationUser loadByToken(String token) throws ResourceNotFoundException {
        ApplicationUser user = userRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with token was not found : " + token)
                );

        return user;
    }

    @Transactional
    public Integer updateRestrictionDays(Integer restrictionDays, Set<Long> ids) {
        Integer updatedRows = userRepository.updateRestrictionDays(restrictionDays, ids);

        return updatedRows;
    }

    @Transactional
    public Integer resetRestrictionDays(Integer restrictionDays) {
        Integer updatedRows = userRepository.resetRestrictionDays(restrictionDays);

        return updatedRows;
    }

    public List<ApplicationUser> getAll() {
        Iterable<ApplicationUser> userIterable = userRepository.findAll();
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        userIterable.forEach(applicationUsers::add);

        return applicationUsers;
    }

    public List<ApplicationUser> getAllByManagerId(Long userId) {
        Iterable<ApplicationUser> userIterable = userRepository.findAllByManagerId(userId);
        List<ApplicationUser> applicationUsers = new ArrayList<>();
        userIterable.forEach(applicationUsers::add);

        return applicationUsers;
    }

    private Set getAuthority(ApplicationUser user) {
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }

    public void deleteUserData(Long id) {
        ApplicationUser user = loadById(id);
        if(!user.getChildren().isEmpty()) {
            throw new UserDeleteException("Leader can't be deleted.");
        }
        List<LearningDay> learningDays = learningDayService.getAllByUserId(id);
        if(!learningDays.isEmpty()) {
            for (LearningDay learningDay : learningDays) {
                learningDayService.delete(learningDay.getId());
            }
        }
        List<Goal> goals = goalService.getAllByUserId(id);
        if(!goals.isEmpty()) {
            for (Goal goal : goals) {
                goalService.delete(goal.getId());
            }
        }
        List<Comment> comments = commentService.getAllByUserId(id);
        if(!comments.isEmpty()) {
            for (Comment comment : comments) {
                commentService.delete(comment.getId());
            }
        }
        ApplicationUser manager = user.getManager();
        manager.getChildren().remove(user);
    }
}
