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
public class Serv {
    @Id
    private UUID id;
    private String name;
    private String description;
    private int time;
    private float price;
}
