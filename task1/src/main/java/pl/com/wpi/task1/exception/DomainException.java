package pl.com.wpi.task1.exception;

public class DomainException extends RuntimeException {
    private final CodeException codeException;

    public DomainException(CodeException codeException, Object... variables) {
        super(String.format(codeException.getDetailPattern(), variables));
        this.codeException = codeException;
    }

    public CodeException getCodeException() {
        return codeException;
    }
}
