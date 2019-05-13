package jm.student.secutiry.utilities;

public class CodeMessenger {
    private static ErrorCode errorCode;

    private static SuccessCode successCode;

    public static ErrorCode getErrorCode() {
        ErrorCode cod = errorCode;
        errorCode = null;
        return cod;
    }

    public static SuccessCode getSuccessCode() {
        SuccessCode cod = successCode;
        successCode = null;
        return cod;
    }

    public static void setErrorCode(ErrorCode cod) {
        errorCode = cod;
    }

    public static void setSuccessCode(SuccessCode cod) {
        successCode = cod;
    }
}


