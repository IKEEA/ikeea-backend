package mif.vu.ikeea.Helper;

import mif.vu.ikeea.Payload.IFilterRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PaginationHelper {

    public Pageable getPageable(IFilterRequest filterRequest) {
        Integer page = filterRequest.getPage();
        Integer size = filterRequest.getSize();

        if (page == null) {
            page = 0;
        }

        if (size == null) {
            size = 10;
        }

        return PageRequest.of(page, size);
    }
}
