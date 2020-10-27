package com.hairdresser.booking.dao;

import com.hairdresser.booking.model.Serv;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fake")
public class FakeServDaoAccessService implements ServDao{

    private List<Serv> servs = new ArrayList<>();

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
