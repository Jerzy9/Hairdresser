package com.hairdresser.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Calendar {

    @Id
    private UUID id = UUID.randomUUID();
    private List<Day> daysAtWork = new ArrayList<>();
    private List<Day> historyOfWork = new ArrayList<>();

}
