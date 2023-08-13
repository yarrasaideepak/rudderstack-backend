package com.rudderstack.backend.Tracking.controller;

import com.rudderstack.backend.Tracking.beans.RequestStatusBean;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;
import com.rudderstack.backend.Tracking.services.impl.TrackingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tracking")
public class TrackingController {

    @Autowired
    TrackingServiceImpl trackingService;

    @RequestMapping("/addTracking")
    @PostMapping
    public RequestStatusBean addTracking(@RequestBody TrackingPlanBean trackingPlan){
        return trackingService.addTrack(trackingPlan);
    }

    @RequestMapping("/getTracking")
    @GetMapping
    public List<TrackingPlanBean> getTracking(){
        return trackingService.getTracking();
    }

    @RequestMapping("/updateTracking")
    @PutMapping
    public RequestStatusBean updateTracking(@RequestBody TrackingPlanBean trackingPlan){
        return trackingService.updateTracking(trackingPlan);
    }


}
