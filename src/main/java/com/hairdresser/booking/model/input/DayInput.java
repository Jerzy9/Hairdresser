package com.hairdresser.booking.model.input;

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
public class DayInput {

    private int start;
    private int end;
    private List<Visit> visits;

}
