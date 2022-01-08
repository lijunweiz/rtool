package com.lijw.decision.core.exception;

public class CannotDecisionException extends Exception {

    public CannotDecisionException() {
        super();
    }

    public CannotDecisionException(String message) {
        super(message);
    }

    public CannotDecisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CannotDecisionException(Throwable cause) {
        super(cause);
    }

}
