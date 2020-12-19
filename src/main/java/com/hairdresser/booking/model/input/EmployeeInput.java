package com.hairdresser.booking.model.input;

import com.hairdresser.booking.model.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeInput {

    private String name;
    private String description;
    private List<UUID> hairstyles;
    private Calendar calendar;

    // Calendar is created without any data or basic configuration(not implemented yet)
    // After Employee has been created, Admin can configure his calendar
}
