package com.lijw.decision.core.exception;

public class DecisionException extends Exception {

    public DecisionException() {
        super();
    }

    public DecisionException(String message) {
        super(message);
    }

    public DecisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DecisionException(Throwable cause) {
        super(cause);
    }

}
