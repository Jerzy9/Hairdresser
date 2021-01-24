package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTokenException extends IllegalStateException {

    public InvalidTokenException(String massage) {
        super(massage);
    }
}
