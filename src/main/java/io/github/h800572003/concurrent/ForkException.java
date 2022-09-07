package io.github.h800572003.concurrent;

import io.github.h800572003.exception.ApBusinessException;

import java.text.MessageFormat;

public class ForkException extends ApBusinessException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ForkException(String pattern, Object... arguments) {
        super(MessageFormat.format(pattern, arguments));
    }

    public ForkException(String string, Throwable throwable) {
        super(string, throwable);
    }

}