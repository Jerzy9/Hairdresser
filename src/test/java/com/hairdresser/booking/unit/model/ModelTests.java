package com.hairdresser.booking.unit.model;

import com.hairdresser.booking.model.Hairstyle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;
import static org.junit.Assert.*;

@SpringBootTest
public class ModelTests {

    private Hairstyle hairstyle;

    @Test
    public void createHairstyle_fulfillWithDate_ReturnData() {
        UUID id = UUID.randomUUID();
        String name = "Haircut";
        String description = "Simple haircut with scissors";
        int time = 30*60*1000;      //30 min in milliseconds
        float price  = (float) 25.99;

        hairstyle = new Hairstyle(id, name, description, time, price);
        assertEquals(hairstyle.getId(), id);
        assertEquals(hairstyle.getName(), name);
        assertEquals(hairstyle.getDescription(), description);
        assertEquals(hairstyle.getTime(), time);
        assertEquals((int) hairstyle.getPrice(), (int) price);
    }
}
