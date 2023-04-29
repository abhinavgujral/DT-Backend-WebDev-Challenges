package com.DTedutech.task1.Controller;

import com.DTedutech.task1.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.DTedutech.task1.Entity.Event;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v3/app")
public class EventController {
    //private final List<Event> events = new ArrayList<>();
    private EventService eventService;

    @Autowired
    EventController(EventService eventService){
                    this.eventService=eventService;
                 }






    @GetMapping("/events")
    public ResponseEntity<?> getEvents(@RequestParam Integer id){
        Event event=eventService.getEventById( id);

        return ResponseEntity.ok(event);
    }
    /**
     *
     * @param type
     * @param limit
     * @param page
     * @return
     */
    @GetMapping("/events")
    public ResponseEntity<?> getEvents(@RequestParam(required = false) String type,
                                       @RequestParam(required = false,defaultValue = "5") int limit,
                                       @RequestParam(required = false,defaultValue = "1") int page) {


            List<Event> events= eventService.getEvents(type,limit,page);

            if (!events.isEmpty()) {
                return ResponseEntity.ok(events);
            }
                return ResponseEntity.notFound().build();

    }




}
