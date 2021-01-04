package com.hairdresser.booking.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client {

    @Id
    private String id;
    private String name;
    private String surname;
    private Long phoneNumber;

}
