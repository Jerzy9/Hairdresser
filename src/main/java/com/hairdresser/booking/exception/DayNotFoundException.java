package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DayNotFoundException extends RuntimeException {

    public DayNotFoundException(String massage) {
        super(massage);
    }
}
