package jp.co.eb.fliggy.exception;

public class ParamNotValidException extends RuntimeException {

    private static final long serialVersionUID = 3294375405326375245L;

    public ParamNotValidException() {
    }

    public ParamNotValidException(String message) {
        super(message);
    }
}
