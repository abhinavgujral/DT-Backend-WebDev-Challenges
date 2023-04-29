package com.DTedutech.task1.Service;

import com.DTedutech.task1.Entity.Event;
import com.DTedutech.task1.Exception.ResourceNotFoundExceptinon;
import com.DTedutech.task1.Repository.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    EventDao eventDao;

    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<Event> getEvents( String type, int limit, int page) {

            Page<Event> eventPage = eventDao.findByType(type, PageRequest.of(page, limit));
            List<Event> events = eventPage.getContent();

            if (!events.isEmpty())
                throw new ResourceNotFoundExceptinon("event with this type is not present");

                return events;
    }

    public Event getEventById(Integer id) {

        Optional<Event> eventbyId = eventDao.findById(id);
        if(eventbyId.isEmpty())
            throw new ResourceNotFoundExceptinon("event with id:"+id+" is not present");
        return  eventbyId.get();
    }
}
