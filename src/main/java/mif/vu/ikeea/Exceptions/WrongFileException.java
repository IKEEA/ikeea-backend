package mif.vu.ikeea.Exceptions;

public class WrongFileException extends RuntimeException {
    public WrongFileException(String message) {
        super(message);
    }

    public WrongFileException(String message, Throwable cause) {
        super(message, cause);
    }
}
