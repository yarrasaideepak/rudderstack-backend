package com.rudderstack.backend.Events.controller;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.beans.Event;
import com.rudderstack.backend.Events.services.impl.EventServiceImpl;
import com.rudderstack.backend.common.beans.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventServiceImpl eventService;

    @RequestMapping("/addEvent")
    @PostMapping
    public Response addEvent(@RequestBody AddEventBean event){
        return eventService.addEvent(event);
    }

    @RequestMapping("/getEvent/{eventName}")
    @GetMapping
    public Object getEvent(@PathVariable String eventName){
        return eventService.getEvent(eventName);
    }

    @RequestMapping("/updateEvent")
    @PutMapping
    public Response updateEvent(@RequestBody Event event){
        return eventService.updateEvent(event);
    }

}
