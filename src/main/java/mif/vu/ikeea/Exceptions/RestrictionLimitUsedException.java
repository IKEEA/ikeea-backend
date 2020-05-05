package mif.vu.ikeea.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RestrictionLimitUsedException extends RuntimeException {
    public RestrictionLimitUsedException(String message) {
        super(message);
    }

    public RestrictionLimitUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}
