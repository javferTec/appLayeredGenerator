package local.javi.app.domain.exception;

public class ExtractionException extends RuntimeException {
    public ExtractionException(String message) {
        super(message);
    }

    public ExtractionException(String message, Throwable cause) {
        super(message, cause);
    }
}
