package my_exceptions;

public class EmtyContainerException extends Exception {
    public EmtyContainerException() {
    }
    public EmtyContainerException(String message) {
        super(message);
    }
}
