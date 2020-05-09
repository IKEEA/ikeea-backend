package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Repository.GoalRepository;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Goal add(Goal goal) {
        return goalRepository.save(goal);
    }

    @Transactional
    public void delete(Long id) {
        goalRepository.deleteById(id);
    }

    @Transactional
    public void update(Goal goal) {
        goalRepository.save(goal);
    }

    @Transactional
    public Goal loadById(Long id) throws ResourceNotFoundException {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Goal was not found")
                );

        return goal;
    }

    public List<Goal> getAll() {
        Iterable<Goal> goalIterable = goalRepository.findAll();
        List<Goal> goals = new ArrayList<>();
        goalIterable.forEach(goals::add);

        return goals;
    }

    public List<Goal> getAllByUserId(Long userId) {
        Iterable<Goal> goalIterable = goalRepository.findAllByUserId(userId);
        List<Goal> goals = new ArrayList<>();
        goalIterable.forEach(goals::add);

        return goals;
    }

    public List<Goal> getAllByManagerId(Long userId){
        List<ApplicationUser> applicationUsers = userService.getAllByManagerId(userId);
        List<Goal> goals = new ArrayList<>();

        for(ApplicationUser applicationUser : applicationUsers){
            Iterable<Goal> goalIterable = goalRepository.findAllByUserId(applicationUser.getId());
            goalIterable.forEach(goals::add);
        }
        return goals;
    }
}
