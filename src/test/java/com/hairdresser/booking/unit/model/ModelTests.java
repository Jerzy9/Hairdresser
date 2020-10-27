package com.hairdresser.booking.unit.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import static org.junit.Assert.*;

@SpringBootTest
public class ModelTests {

    private Serv serv;

    @Test
    public void createServ_fulfillWithDate_ReturnData() {
        UUID id = UUID.randomUUID();
        String name = "Haircut";
        String description = "Simple haircut with scissors";
        int time = 30*60*1000;      //30 min in milliseconds
        float price  = (float) 25.99;

        serv = new Serv(id, name, description, time, price);
        assertEquals(serv.getId(), id);
        assertEquals(serv.getName(), name);
        assertEquals(serv.getDescription(), description);
        assertEquals(serv.getTime(), time);
        assertEquals((int) serv.getPrice(), (int) price);
    }
}
