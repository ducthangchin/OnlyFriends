package com.ducthangchin.service;

import com.ducthangchin.model.Profile;
import com.ducthangchin.model.StatusUpdate;
import com.ducthangchin.model.StatusUpdateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class StatusUpdateService {

    private final static int PAGESIZE = 5;

    @Autowired
    private StatusUpdateDao statusUpdateDao;

    public StatusUpdate getStatus(Long id) {
        return statusUpdateDao.findById(id).get();
    }


    public void save(StatusUpdate statusUpdate) {
        statusUpdateDao.save(statusUpdate);
    }

    public StatusUpdate getLatest() {
        return statusUpdateDao.findFirstByOrderByAddedDesc();
    }

    public Page<StatusUpdate> getPage(int pageNumber) {
        PageRequest request = new PageRequest(pageNumber-1, PAGESIZE, Sort.Direction.DESC, "added");

        return statusUpdateDao.findAll(request);
    }

    public void delete(Long id) {
        statusUpdateDao.deleteById(id);
    }


    public List<StatusUpdate> getByOwner(Profile user) {
        List<StatusUpdate> statusUpdates = new ArrayList<>();
        statusUpdateDao.findAllByOwner(user).forEach(statusUpdate -> statusUpdates.add(statusUpdate));
        statusUpdates.sort(Comparator.comparing(StatusUpdate::getAdded));
        return statusUpdates;
    }


}
