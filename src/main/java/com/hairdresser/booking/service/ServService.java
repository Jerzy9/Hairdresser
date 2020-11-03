package com.hairdresser.booking.service;

import com.hairdresser.booking.dao.ServDao;
import com.hairdresser.booking.model.Serv;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServService {
    @Autowired @Qualifier("fake")
    private final ServDao servDao;

    public int insertServ(Serv newServ) {
        return servDao.insertServ(newServ);
    }

    public Optional<Serv> getServById(UUID id) {
        return servDao.getServById(id);
    }

    public List<Serv> getAllServs() {
        return servDao.getAllServs();
    }

    public int deleteServById(UUID id) {
        return servDao.deleteServById(id);
    }
}
