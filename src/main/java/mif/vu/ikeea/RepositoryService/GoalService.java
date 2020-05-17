package mif.vu.ikeea.RepositoryService;

import mif.vu.ikeea.Entity.ApplicationUser;
import mif.vu.ikeea.Entity.Goal;
import mif.vu.ikeea.Entity.Repository.GoalRepository;
import mif.vu.ikeea.Exceptions.ResourceNotFoundException;
import mif.vu.ikeea.Helper.PaginationHelper;
import mif.vu.ikeea.Payload.FilterGoalRequest;
import mif.vu.ikeea.Specifications.GoalSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoalService {
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private PaginationHelper paginationHelper;

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

    public List<Goal> getAll(Long managerId, FilterGoalRequest filterGoalRequest) {
        Pageable pageable = paginationHelper.getPageableGoal(filterGoalRequest);

        List<Goal> goalsAll = goalRepository.findAll(Specification.where(GoalSpecification.withManager(managerId))
                .and(Specification.where(GoalSpecification.withTopic(filterGoalRequest.getTopicId())))
                .and(Specification.where(GoalSpecification.withUser(filterGoalRequest.getUserId()))));

        ApplicationUser manager = userService.loadById(managerId);
        List<ApplicationUser> childUsers = manager.getChildren();

        for (ApplicationUser applicationUser : childUsers) {
            List<Goal> goalsUsers = goalRepository.findAll(Specification.where(GoalSpecification.withManager(applicationUser.getId()))
                    .and(Specification.where(GoalSpecification.withTopic(filterGoalRequest.getTopicId())))
                    .and(Specification.where(GoalSpecification.withUser(filterGoalRequest.getUserId()))));

            for (Goal goal : goalsUsers) {
                goalsAll.add(goal);
            }

        }

        int start = (int) pageable.getOffset();
        int end = (start + pageable.getPageSize()) > goalsAll.size() ? goalsAll.size() : (start + pageable.getPageSize());

        Page<Goal> goalsAllPage = new PageImpl<Goal>(goalsAll.subList(start, end), pageable, goalsAll.size());

        List<Goal> goals = goalsAllPage.getContent();

        return goals;
    }

    public List<Goal> getAllByUserId(Long userId) {
        Iterable<Goal> goalIterable = goalRepository.findAllByUserId(userId);
        List<Goal> goals = new ArrayList<>();
        goalIterable.forEach(goals::add);

        return goals;
    }
}
