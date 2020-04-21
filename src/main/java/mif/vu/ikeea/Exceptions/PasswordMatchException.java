package mif.vu.ikeea.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordMatchException  extends RuntimeException {
    public PasswordMatchException(String message) {
        super(message);
    }

    public PasswordMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
