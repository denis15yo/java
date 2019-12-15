package my_exceptions;

public class IncorrectSizeOfContainerException extends Exception {
    public IncorrectSizeOfContainerException() {
    }
    public IncorrectSizeOfContainerException(String message) {
        super(message);
    }
}
