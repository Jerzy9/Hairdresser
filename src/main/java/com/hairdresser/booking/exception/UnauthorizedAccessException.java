package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UnauthorizedAccessException extends RuntimeException {

    public UnauthorizedAccessException(String massage) {
        super(massage);
    }
}
