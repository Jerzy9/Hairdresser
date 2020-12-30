package com.hairdresser.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {

    @Id
    private String id;
    private String name;
    private String description;
    private List<String> hairstyles;      //it stores only IDs, to avoid duplicating objects and lack of synchronization
    private Calendar calendar;
}
