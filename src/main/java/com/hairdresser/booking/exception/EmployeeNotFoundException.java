package com.hairdresser.booking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String massage) {
        super(massage);
    }
}
