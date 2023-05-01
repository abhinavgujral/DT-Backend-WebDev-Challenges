package com.DTedutech.task1.Controller;

import com.DTedutech.task1.DTO.EventDTO;
import com.DTedutech.task1.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.DTedutech.task1.Entity.Event;
import java.util.List;


@RestController
@RequestMapping("/api/v3/app")

public class EventController {

    private EventService eventService;

    @Autowired
    EventController(EventService eventService){
                    this.eventService=eventService;
                 }




    /**
     *
     * @param type
     * @param limit
     * @param page
     * @return
     */
    @GetMapping("/events")
    public ResponseEntity<?> getEvents(@RequestParam(name="id",required=false) Integer id,
                                        @RequestParam(name="type",required = false) String type,
                                       @RequestParam(name = "limit",required = false,defaultValue = "5") int limit,
                                       @RequestParam(name="page",required = false,defaultValue = "1") int page) {


           List<EventDTO> events= eventService.getEvents(id,type,limit,page);
        
            if (!events.isEmpty()) {
                return ResponseEntity.ok(events);
            }
                return ResponseEntity.notFound().build();

    }

 @PostMapping("/events")
public ResponseEntity<?> createEvent(@RequestBody Event event) {

        EventDTO createdEvent=eventService.createEvent(event);
        return new ResponseEntity<>(createdEvent.getId(),HttpStatus.CREATED);

 }
    @PutMapping("/events/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Integer id, @RequestBody Event event) {
       EventDTO updatedEvent= eventService.updateEvent(id,event);
        return new ResponseEntity<>(updatedEvent.getId(),HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/events/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Integer id) {
     EventDTO deletedEvent=   eventService.deleteEvent(id);
        return new ResponseEntity<>(deletedEvent,HttpStatus.OK);
    }
}
