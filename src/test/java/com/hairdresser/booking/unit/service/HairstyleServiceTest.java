package com.hairdresser.booking.unit.service;

import com.hairdresser.booking.dao.HairstyleDao;
import com.hairdresser.booking.service.HairstyleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

@SpringBootTest
public class HairstyleServiceTest {

    @Autowired
    private HairstyleService hairstyleService;

    @MockBean
    private HairstyleDao hairstyleDao;

    @Test
    public void insertHairstyle_InputWithAllVars_returnHairstyle() {
        String id = UUID.randomUUID().toString();
        String name = "Haircut";
        String description = "Simple haircut with scissors";
        int time = 30*60*1000;      //30 min in milliseconds
        float price  = (float) 25.99;

//        HairstyleInput hairstyleInput = new HairstyleInput(name, description, time, price);
//        Hairstyle returnHairstyle = new Hairstyle(id, name, description, time, price);
//
//        Mockito.when(hairstyleDao.insertHairstyle(returnHairstyle)).thenReturn(returnHairstyle);
//        assertEquals(returnHairstyle, hairstyleService.insertHairstyle(hairstyleInput));
    }

    @Test
    public void getServById_IdIsCorrect_returnServ() {

    }

    // etc...
    // I don't write every test for now, because service's methods are pretty basic right now,
}
