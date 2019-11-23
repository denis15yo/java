package myExceptions;

public class EmtyContainerException extends Exception {
    public EmtyContainerException() {
        super();
    }
    public EmtyContainerException(String message) {
        super(message);
    }
}
