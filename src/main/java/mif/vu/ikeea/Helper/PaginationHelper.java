package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Payload.FilterGoalRequest;
import mif.vu.ikeea.Payload.FilterLearningDayRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationHelper {

    Pageable pageable = PageRequest.of(0, 10);

    public Pageable getPageableLearningDay(FilterLearningDayRequest filterLearningDayRequest) {

        if(filterLearningDayRequest.getPage() != null && filterLearningDayRequest.getSize() != null) {
            pageable = PageRequest.of(filterLearningDayRequest.getPage(), filterLearningDayRequest.getSize());
        }
        else if(filterLearningDayRequest.getPage() != null) {
            pageable = PageRequest.of(filterLearningDayRequest.getPage(), 10);
        }
        return pageable;
    }

    public Pageable getPageableGoal(FilterGoalRequest filterGoalRequest) {

        if(filterGoalRequest.getPage() != null && filterGoalRequest.getSize() != null) {
            pageable = PageRequest.of(filterGoalRequest.getPage(), filterGoalRequest.getSize());
        }
        else if(filterGoalRequest.getPage() != null) {
            pageable = PageRequest.of(filterGoalRequest.getPage(), 10);
        }
        return pageable;
    }
}
