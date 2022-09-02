package io.github.h800572003.concurrent;

import java.io.IOException;

public class WorkLatchException extends IOException {
    public WorkLatchException() {
    }

    public WorkLatchException(String message) {
        super(message);
    }

    public WorkLatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public WorkLatchException(Throwable cause) {
        super(cause);
    }
}
