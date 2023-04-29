package com.DTedutech.task1.Controller;

import com.DTedutech.task1.Service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.DTedutech.task1.Entity.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getEvents1(@RequestParam(required=false) Integer id,
                                        @RequestParam(required = false) String type,
                                       @RequestParam(required = false,defaultValue = "5") int limit,
                                       @RequestParam(required = false,defaultValue = "1") int page) {


           List<Event> events= eventService.getEvents(id,type,limit,page);
        
            if (!events.isEmpty()) {
                return ResponseEntity.ok(events);
            }
                return ResponseEntity.notFound().build();

    }




}
