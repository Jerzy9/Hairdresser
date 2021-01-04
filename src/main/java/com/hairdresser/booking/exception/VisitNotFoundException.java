package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VisitNotFoundException extends RuntimeException {
    public VisitNotFoundException(String massage) {
        super(massage);
    }
}
