package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Serv;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fake")
public class FakeServDaoAccessService implements ServDao{

    private List<Serv> servs = Arrays.asList(
                new Serv(UUID.randomUUID(), "first", "first desc", 35, 20),
                new Serv(UUID.randomUUID(), "second", "second desc", 55, 60),
                new Serv(UUID.randomUUID(), "third", "third desc", 150, 45),
                new Serv(UUID.randomUUID(), "forth", "forth desc", 180, 20),
                new Serv(UUID.randomUUID(), "fifth", "fifth desc", 35, 60),
                new Serv(UUID.randomUUID(), "sixth", "sixth desc", 70, 45),
                new Serv(UUID.randomUUID(), "seventh", "seventh desc", 20, 20),
                new Serv(UUID.randomUUID(), "eighth", "eighth desc", 50, 60),
                new Serv(UUID.randomUUID(), "ninth", "ninth desc", 15, 45),
                new Serv(UUID.randomUUID(), "tenth", "tenth desc", 40, 45)
            );

    @Override
    public int insertServ(Serv newServ) {
        servs.add(newServ);
        return 1;
    }

    @Override
    public Optional<Serv> getServById(UUID id) {
        return servs.stream().filter(serv -> serv.getId().equals(id)).findFirst();
    }

    @Override
    public List<Serv> getAllServs() {
        return servs;
    }

    @Override
    public int deleteServById(UUID id) {
        Optional<Serv> servToDelete = getServById(id);

        if(servToDelete.isEmpty())
            return 0;

        servs.remove(servToDelete.get());
        return 1;
    }
}
