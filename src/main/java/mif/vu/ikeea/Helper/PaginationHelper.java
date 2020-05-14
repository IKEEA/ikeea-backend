package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Payload.FilterGoalRequest;
import mif.vu.ikeea.Payload.FilterLearningDayRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationHelper {
    private final int DEFAULT_PAGE = 0;
    private final int DEFAULT_SIZE = 10;

    public Pageable getPageableLearningDay(FilterLearningDayRequest filterLearningDayRequest) {
        Integer page = filterLearningDayRequest.getPage();
        Integer size = filterLearningDayRequest.getSize();

        if (page == null) {
            page = DEFAULT_PAGE;
        }

        if (size == null) {
            size = DEFAULT_SIZE;
        }

        return PageRequest.of(page, size);
    }

    public Pageable getPageableGoal(FilterGoalRequest filterGoalRequest) {
        Integer page = filterGoalRequest.getPage();
        Integer size = filterGoalRequest.getSize();

        if (page == null) {
            page = DEFAULT_PAGE;
        }

        if (size == null) {
            size = DEFAULT_SIZE;
        }

        return PageRequest.of(page, size);
    }
}
