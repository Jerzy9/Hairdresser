package com.hairdresser.booking.model;

import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.data.annotation.Id;

import java.util.UUID;

public class Client {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private Long phoneNumber;

}
