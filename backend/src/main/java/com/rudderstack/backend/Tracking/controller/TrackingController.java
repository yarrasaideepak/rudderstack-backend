package com.rudderstack.backend.Tracking.controller;

import com.rudderstack.backend.common.beans.Response;
import com.rudderstack.backend.Tracking.beans.TrackingPlan;
import com.rudderstack.backend.Tracking.services.impl.TrackingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    TrackingServiceImpl trackingService;

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/addTracking")
    @PostMapping
    public Response addTracking(@RequestBody TrackingPlan trackingPlan){
        return trackingService.addTrack(trackingPlan);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping("/getTracking/{trackName}")
    @GetMapping
    public Object getTracking(@PathVariable String trackName){
        return trackingService.getTracking(trackName);
    }

    @RequestMapping("/updateTracking")
    @PutMapping
    public Response updateTracking(@RequestBody TrackingPlan trackingPlan){
        return trackingService.updateTracking(trackingPlan);
    }

}
