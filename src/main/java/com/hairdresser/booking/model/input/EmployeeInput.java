package com.hairdresser.booking.model.input;

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
    // Calendar has its own Input class

}
