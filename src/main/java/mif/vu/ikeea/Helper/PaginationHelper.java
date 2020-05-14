package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Payload.FilterGoalRequest;
import mif.vu.ikeea.Payload.FilterLearningDayRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationHelper {

    Integer page;
    Integer size;

    public Pageable getPageableLearningDay(FilterLearningDayRequest filterLearningDayRequest) {
        if(filterLearningDayRequest.getPage() == null) {
            page = 0;
        }
        if (filterLearningDayRequest.getSize() == null) {
            size = 10;
        }
        if(filterLearningDayRequest.getPage() != null) {
            page = filterLearningDayRequest.getPage();
        }
        if(filterLearningDayRequest.getSize() != null) {
            size = filterLearningDayRequest.getSize();
        }

        return PageRequest.of(page, size);
    }

    public Pageable getPageableGoal(FilterGoalRequest filterGoalRequest) {
        if(filterGoalRequest.getPage() == null) {
            page = 0;
        }
        if (filterGoalRequest.getSize() == null) {
            size = 10;
        }
        if(filterGoalRequest.getPage() != null) {
            page = filterGoalRequest.getPage();
        }
        if(filterGoalRequest.getSize() != null) {
            size = filterGoalRequest.getSize();
        }

        return PageRequest.of(page, size);
    }
}
