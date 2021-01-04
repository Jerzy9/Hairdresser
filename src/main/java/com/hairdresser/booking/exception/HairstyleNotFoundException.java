package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HairstyleNotFoundException extends RuntimeException {

    public HairstyleNotFoundException(String message) {
        super(message);
    }
}
