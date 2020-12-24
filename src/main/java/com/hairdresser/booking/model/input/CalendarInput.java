package com.hairdresser.booking.model.input;

import com.hairdresser.booking.model.Day;
import com.hairdresser.booking.model.Visit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CalendarInput {

    private List<Visit> visits;
    private List<Day> workDays;
}
