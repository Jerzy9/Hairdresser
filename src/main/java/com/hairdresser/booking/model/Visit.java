package com.hairdresser.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Visit {

    @Id
    private UUID id = UUID.randomUUID();
    private UUID client;
    private UUID hairstyle;
    private int start;
    private int end;
    private String description;

}
