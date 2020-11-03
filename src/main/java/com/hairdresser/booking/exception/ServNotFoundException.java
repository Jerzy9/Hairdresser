package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServNotFoundException extends RuntimeException {
    public ServNotFoundException(String message) {
        super(message);
    }
}
