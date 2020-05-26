package mif.vu.ikeea.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LeaderDeleteException extends RuntimeException {
    public LeaderDeleteException(String message) {
        super(message);
    }

    public LeaderDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
