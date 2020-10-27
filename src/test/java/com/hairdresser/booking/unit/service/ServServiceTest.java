package com.hairdresser.booking.unit.service;

import com.hairdresser.booking.unit.dao.ServDao;
import com.hairdresser.booking.unit.model.Serv;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.junit.Assert.*;

@SpringBootTest
public class ServServiceTest {

    @Autowired
    private ServService servService;

    @MockBean
    private ServDao servDao;

    @Test
    public void insertServ_WithAllVars_return1() {
        UUID id = UUID.randomUUID();
        String name = "Haircut";
        String description = "Simple haircut with scissors";
        int time = 30*60*1000;      //30 min in milliseconds
        float price  = (float) 25.99;

        Serv serv = new Serv(id, name, description, time, price);

        Mockito.when(servDao.insertServ(serv)).thenReturn(1);
        assertEquals(1, servService.insertServ(serv));
    }

    @Test
    public void getServById_IdIsCorrect_returnServ() {

    }

    // etc...
    // I don't write every test for now, because service's methods are pretty basic right now,
    // It's better to write test when these will do some extra stuff
}
