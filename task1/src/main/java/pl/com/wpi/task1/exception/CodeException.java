package pl.com.wpi.task1.exception;

public enum CodeException {
    VALUE_IS_TOO_SMALL("T_001", "For localization %d the value %d is smaller than %d placed in DB", 400);
    private String code;
    private String detailPattern;
    private int httpStatus;

    CodeException(String code, String detailPattern, int httpStatus) {
        this.code = code;
        this.detailPattern = detailPattern;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetailPattern() {
        return detailPattern;
    }

    public void setDetailPattern(String detailPattern) {
        this.detailPattern = detailPattern;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
