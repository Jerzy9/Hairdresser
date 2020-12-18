package com.hairdresser.booking.model.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HairstyleInput {

    private String name;
    private String description;
    private int time;
    private float price;
}