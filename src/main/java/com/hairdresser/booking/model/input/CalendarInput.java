package com.hairdresser.booking.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarInput {

    private UUID id;

    //It will be implemented in near future..
    //And id will be missing
}
