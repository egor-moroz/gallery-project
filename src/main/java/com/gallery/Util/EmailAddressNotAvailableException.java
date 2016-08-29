package com.gallery.Util;

public class EmailAddressNotAvailableException extends Exception {

    public EmailAddressNotAvailableException() {
    }

    public EmailAddressNotAvailableException(String message) {
        super(message);
    }

    public EmailAddressNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAddressNotAvailableException(Throwable cause) {
        super(cause);
    }

    public EmailAddressNotAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
