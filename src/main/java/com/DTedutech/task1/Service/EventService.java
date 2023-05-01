package com.DTedutech.task1.Service;

import com.DTedutech.task1.Entity.Event;
import com.DTedutech.task1.Exception.InsufficientResourceException;
import com.DTedutech.task1.Exception.ResourceNotFoundExceptinon;
import com.DTedutech.task1.Repository.EventDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.InsufficientResourcesException;
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

    public List<Event> getEvents( Integer id,String type, int limit, int page) {
        if(id==null && type.isEmpty())
            throw new InsufficientResourceException("Add relevant parameters in URI");
        if(id==null) {
            Page<Event> eventPage = eventDao.findByType(type, PageRequest.of(page, limit));
            List<Event> events = eventPage.getContent();

            if (!events.isEmpty())
                throw new ResourceNotFoundExceptinon("event with this type is not present");

            return events;
        }
        else{
            Optional<Event> eventbyId = eventDao.findById(id);
            if(eventbyId.isEmpty())
                throw new ResourceNotFoundExceptinon("event with id:"+id+" is not present");
            return  new ArrayList<>(Arrays.asList(eventbyId.get()));
        }
    }

    public Event createEvent(Event event) {

        return eventDao.save(event);

    }


    public Event updateEvent(Integer EventId, Event event) {
        Event fetchedEvent= eventDao.findById(EventId).orElseThrow(()->new ResourceNotFoundExceptinon("Event not found"));
        return eventDao.save(event);
    }


}
