package jp.co.eb.fliggy.exception;

public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = 7842186253615652590L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
