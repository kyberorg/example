package io.kyberorg.example.service;

import io.kyberorg.example.util.SystemStatus;
import io.kyberorg.example.dao.StatusDao;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class StatusService {
    private final StatusDao statusDao;

    public StatusService(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    public SystemStatus getApplicationStatus() {
        //if can execute this - Application is up and running
        return SystemStatus.UP;
    }

    public SystemStatus getDatabaseStatus() {
        boolean rawStatusFromDb = statusDao.findOne().getStatus();
        return SystemStatus.fromBoolean(rawStatusFromDb);
    }

    public SystemStatus getRandomStatus() {
       boolean rawStatus = ThreadLocalRandom.current().nextBoolean();
       return SystemStatus.fromBoolean(rawStatus);
    }
}
