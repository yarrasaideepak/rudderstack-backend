package com.rudderstack.backend.Tracking.services.impl;

import com.rudderstack.backend.Events.beans.Event;
import com.rudderstack.backend.common.Constants.Constants;
import com.rudderstack.backend.common.Constants.ErrorCodes;
import com.rudderstack.backend.common.mapper.CommonMapper;
import com.rudderstack.backend.Events.services.impl.EventServiceImpl;
import com.rudderstack.backend.common.beans.Response;
import com.rudderstack.backend.Tracking.beans.TrackingPlan;
import com.rudderstack.backend.Tracking.services.TrackingService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
@Setter
public class TrackingServiceImpl implements TrackingService {

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    CommonMapper commonMapper;

    private HashMap<String, TrackingPlan> trackingDetails = new HashMap<>();

    public Response addTrack(TrackingPlan trackingPlan){

        if(trackingDetails.containsKey(trackingPlan.getTracking_plan().getDisplay_name())){
            return Response.builder().Error(ErrorCodes.TRACK_WITH_SAME_DISPLAY_EXISTS.getErrorMessage())
                    .ErrorCode(ErrorCodes.TRACK_WITH_SAME_DISPLAY_EXISTS.getErrorCode()).build();
        }

        Response response = commonMapper.addEvent(trackingPlan.getTracking_plan().getRules().getEvents());

        if(!Objects.isNull(response.getError())){return response;}


        List<String> eventNames = new ArrayList<>();
        for(Event event: trackingPlan.getTracking_plan().getRules().getEvents()){
            eventNames.add(event.getName());
        }

        trackingPlan.getTracking_plan().getRules().setEvents(new ArrayList<>());
        trackingPlan.getTracking_plan().getRules().setEventNames(eventNames);

        trackingDetails.put(trackingPlan.getTracking_plan().getDisplay_name(),trackingPlan);

        return Response.builder().message(Constants.TRACK_SUCCESSFULLY_ADDED).build();
    }

    public Object getTracking(String trackName){

        if(!trackingDetails.containsKey(trackName)){
            return Response.builder().Error(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorMessage())
                    .ErrorCode(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorCode()).build();
        }

        TrackingPlan orgTrackingPlan = trackingDetails.get(trackName);
        List<String> eventNames = orgTrackingPlan.getTracking_plan().getRules().getEventNames();

        List<Event> eventList = new ArrayList<>();

        for(String eventName: eventNames){
            eventList.add(eventService.getEventDetails().get(eventName));
        }

        TrackingPlan trackingPlan = new TrackingPlan();

        TrackingPlan.Tracking_Plan tracking_plan = new TrackingPlan.Tracking_Plan();
        TrackingPlan.Tracking_Plan.Rules rules = new TrackingPlan.Tracking_Plan.Rules();
        rules.setEvents(eventList);
        rules.setEventNames(null);
        tracking_plan.setRules(rules);
        tracking_plan.setDisplay_name(orgTrackingPlan.getTracking_plan().getDisplay_name());

        trackingPlan.setTracking_plan(tracking_plan);

        return trackingPlan;

    }

    public Response updateTracking(TrackingPlan trackingPlan){

        if(!trackingDetails.containsKey(trackingPlan.getTracking_plan().getDisplay_name())){
            return Response.builder().Error(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorMessage())
                    .ErrorCode(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorCode()).build();
        }

        String trackName = trackingPlan.getTracking_plan().getDisplay_name();

        TrackingPlan orgTrackingPlan = trackingDetails.get(trackName);
        List<String> eventNames = orgTrackingPlan.getTracking_plan().getRules().getEventNames();

        for(String eventName: eventNames){
            int currCount = eventService.getEventDetails().get(eventName).getCount();
            eventService.getEventDetails().get(eventName).setCount(currCount-1);
        }

        Response response = commonMapper.addEvent(trackingPlan.getTracking_plan().getRules().getEvents());

        if(!Objects.isNull(response.getError())){return response;}


        List<String> newEventNames = new ArrayList<>();
        for(Event event: trackingPlan.getTracking_plan().getRules().getEvents()){
            newEventNames.add(event.getName());
        }

        trackingPlan.getTracking_plan().getRules().setEvents(new ArrayList<>());
        trackingPlan.getTracking_plan().getRules().setEventNames(newEventNames);

        trackingDetails.put(trackingPlan.getTracking_plan().getDisplay_name(),trackingPlan);

        return response;

    }

}
