package com.rudderstack.backend.Events.controller;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.services.EventService;
import com.rudderstack.backend.Events.services.impl.EventServiceImpl;
import com.rudderstack.backend.Tracking.beans.RequestStatusBean;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;
import com.rudderstack.backend.Tracking.services.impl.TrackingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    EventServiceImpl eventService;

    @RequestMapping("/addEvent")
    @PostMapping
    public RequestStatusBean addEvent(@RequestBody AddEventBean event){
        return eventService.addEvent(event);
    }

    @RequestMapping("/getEvent")
    @GetMapping
    public List<TrackingPlanBean.Tracking_Plan.Rules.Events> getEvent(){
        return eventService.getEvent();
    }

    @RequestMapping("/updateEvent")
    @PutMapping
    public RequestStatusBean updateEvent(@RequestBody TrackingPlanBean.Tracking_Plan.Rules.Events event){
        return eventService.updateEvent(event);
    }

}
