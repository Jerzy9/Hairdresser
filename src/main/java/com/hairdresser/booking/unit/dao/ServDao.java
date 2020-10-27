package com.hairdresser.booking.unit.dao;

import com.hairdresser.booking.unit.model.Serv;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServDao {

    int insertServ(Serv newServ);

    Optional<Serv> getServById(UUID id);

    List<Serv> getAllServs();

    int deleteServById(UUID id);
}
