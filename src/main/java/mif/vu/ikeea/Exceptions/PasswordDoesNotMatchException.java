package mif.vu.ikeea.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordDoesNotMatchException  extends RuntimeException {
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }

    public PasswordDoesNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
