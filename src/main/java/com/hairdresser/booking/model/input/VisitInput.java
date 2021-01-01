package com.hairdresser.booking.model.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisitInput {

    private String client;
    private String hairstyle;
    private int start;
    private int end;
    private String description;
}
