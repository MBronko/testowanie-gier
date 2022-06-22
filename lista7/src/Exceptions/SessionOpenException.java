package Exceptions;

public class SessionOpenException extends Exception {
    public SessionOpenException(String errorMessage) {
        super(errorMessage);
    }
}
