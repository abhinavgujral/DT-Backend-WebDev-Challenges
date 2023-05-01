package com.DTedutech.task1.Service;

import com.DTedutech.task1.DTO.EventDTO;
import com.DTedutech.task1.Entity.Event;
import com.DTedutech.task1.Entity.File;
import com.DTedutech.task1.Exception.InsufficientResourceException;
import com.DTedutech.task1.Exception.ResourceNotFoundExceptinon;
import com.DTedutech.task1.Repository.EventDao;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    EventDao eventDao;
       @Autowired
    ModelMapper modelMapper;
    @Autowired
    public EventService(EventDao eventDao) {
        this.eventDao = eventDao;
    }

    public List<EventDTO> getEvents(Integer id, String type, int limit, int page) {
        if(id==null && type.isEmpty())
            throw new InsufficientResourceException("Add relevant parameters in URI");
        if(id==null) {
            Page<Event> eventPage = eventDao.findByType(type, PageRequest.of(page, limit));
            List<Event> events = eventPage.getContent();
            if (events.isEmpty())
                throw new ResourceNotFoundExceptinon("event with this type is not present");
            List<EventDTO> eventDTOList=modelMapper.map(events, new TypeToken<List<EventDTO>>() {}.getType());


            return eventDTOList;
        }
        else{
            Optional<Event> eventbyId = eventDao.findById(id);
            if(eventbyId.isEmpty())
                throw new ResourceNotFoundExceptinon("event with id:"+id+" is not present");
            EventDTO eventDTO= new EventDTO();
            mapEventToDto(eventbyId.get(), eventDTO);
            return  new ArrayList<>(Arrays.asList(eventDTO));
        }
    }

    public EventDTO createEvent(Event event) {
        EventDTO eventDTO= new EventDTO();
       mapEventToDto(eventDao.save(event),eventDTO);
        return eventDTO;

    }


    public EventDTO updateEvent(Integer EventId, Event event) {
        Event fetchedEvent= eventDao.findById(EventId).orElseThrow(
                ()->new ResourceNotFoundExceptinon("Event not found"));
        EventDTO eventDTO= new EventDTO();
        mapEventToDto(eventDao.save(event),eventDTO);
        return eventDTO;
    }


    public EventDTO deleteEvent(Integer EventId) {
        Event fetchedEvent= eventDao.findById(EventId).orElseThrow(()->new ResourceNotFoundExceptinon("Event not found"));
         eventDao.deleteById(EventId);
        EventDTO eventDTO= new EventDTO();
        mapEventToDto(eventDao.save(fetchedEvent),eventDTO);
         return  eventDTO;
    }
    public void mapEventToDto(Event event,EventDTO eventDTO){
        eventDTO.setId(event.getId());
        eventDTO.setCategory(event.getCategory());
        eventDTO.setName(event.getName());
        eventDTO.setDescription(event.getDescription());
        eventDTO.setModerator(event.getModerator());
        eventDTO.setSchedule(event.getSchedule());
        eventDTO.setTagline(event.getTagline());
        eventDTO.setSubCategory(event.getSubCategory());
        eventDTO.setRigorRank(event.getRigorRank());
        eventDTO.setFile( event.getFile());

    }
    public void mapDtoToEvent(EventDTO eventDTO,Event event) throws IOException {
        event.setId(eventDTO.getId());
        event.setCategory(eventDTO.getCategory());
        event.setName(eventDTO.getName());
        event.setDescription(eventDTO.getDescription());
        event.setModerator(eventDTO.getModerator());
        event.setSchedule(eventDTO.getSchedule());
        event.setTagline(eventDTO.getTagline());
        event.setSubCategory(eventDTO.getSubCategory());
        event.setRigorRank(eventDTO.getRigorRank());
        event.setFile(eventDTO.getFile());
    }
}
