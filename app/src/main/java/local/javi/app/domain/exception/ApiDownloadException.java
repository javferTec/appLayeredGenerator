package local.javi.app.domain.exception;

public class ApiDownloadException extends RuntimeException {
    public ApiDownloadException(String message) {
        super(message);
    }

    public ApiDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
