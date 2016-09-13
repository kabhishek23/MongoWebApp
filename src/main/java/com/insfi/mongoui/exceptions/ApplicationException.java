package com.insfi.mongoui.exceptions;

public class ApplicationException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    /**
     * Creates a new ApplicationException with errorCode and message.
     *
     * @param errorCode : ErrorCode of the Exception thrown
     * @param message   : A description about the Error.
     */

    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * Creates a new ApplicationException with errorCode and message and
     * appending the cause of a caught exception.
     *
     * @param errorCode : ErrorCode of the Exception thrown
     * @param message   : A description about the Error.
     * @param cause     : Cause of the previous Exception. This is appended in the new
     *                  DatabaseException formed here.
     */

    public ApplicationException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * Return the error code of an exception.
     *
     * @return the errorCode
     */
    public String getErrorCode() {
        return this.errorCode;
    }

}
