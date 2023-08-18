package com.rudderstack.backend.common.mapper;

import com.rudderstack.backend.Events.beans.Event;
import com.rudderstack.backend.Events.services.impl.EventServiceImpl;
import com.rudderstack.backend.Tracking.services.impl.TrackingServiceImpl;
import com.rudderstack.backend.common.Constants.Constants;
import com.rudderstack.backend.common.Constants.ErrorCodes;
import com.rudderstack.backend.common.beans.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class CommonMapper {

    @Autowired EventServiceImpl eventService;
    @Autowired TrackingServiceImpl trackingService;

    public Response addEvent(List<Event> events){

        HashMap<String, Event> eventDetails = eventService.getEventDetails();

        for(Event event:events){
            if(eventDetails.containsKey(event.getName()) && !Objects.equals(event.getRules(),eventDetails.get(event.getName()).getRules())) {
                return Response.builder().Error(ErrorCodes.EVENT_ALREADY_EXISTS_NOT_EQUAL.getErrorMessage())
                        .ErrorCode(ErrorCodes.EVENT_ALREADY_EXISTS_NOT_EQUAL.getErrorCode()).build();
            }
        }

        for(Event event:events){

            if(eventDetails.containsKey(event.getName())){
                event.setCount(eventDetails.get(event.getName()).getCount()+1);
            }
            else{
                event.setCount(1);
            }
            eventDetails.put(event.getName(),event);
        }

        return Response.builder().message(Constants.TRACK_SUCCESSFULLY_ADDED_UPDATED).build();
    }

    public Response addEventInTrack(List<String> trackNames, String eventName){

        for(String trackName: trackNames){

            if(!trackingService.getTrackingDetails().containsKey(trackName)){
                return Response.builder().Error(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorMessage())
                        .ErrorCode(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorCode()).build();
            }

        }

        for(String trackName: trackNames){
            trackingService.getTrackingDetails().get(trackName)
                    .getTracking_plan().getRules().getEventNames().add(eventName);
        }

        return Response.builder().message(Constants.EVENT_SUCCESSFULLY_ADDED).build();

    }

}
