package com.rudderstack.backend.Events.services.impl;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.beans.Event;
import com.rudderstack.backend.common.Constants.Constants;
import com.rudderstack.backend.common.Constants.ErrorCodes;
import com.rudderstack.backend.common.mapper.CommonMapper;
import com.rudderstack.backend.Events.services.EventService;
import com.rudderstack.backend.common.beans.Response;
import com.rudderstack.backend.Tracking.services.impl.TrackingServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Getter
@Setter
public class EventServiceImpl implements EventService {

    @Autowired
    TrackingServiceImpl trackingService;
    @Autowired
    CommonMapper commonMapper;

    private HashMap<String, Event> eventDetails = new HashMap<>();

    public Response addEvent(AddEventBean event){

        if(eventDetails.containsKey(event.getEvent().getName()) && eventDetails.get(event.getEvent().getName()).getCount()>=1){
            return Response.builder().Error(ErrorCodes.EVENT_ALREADY_EXISTS.getErrorMessage())
                    .ErrorCode(ErrorCodes.EVENT_ALREADY_EXISTS.getErrorCode()).build();
        }

        List<String> trackNames = event.getTracks();
        event.getEvent().setCount(1);
        eventDetails.put(event.getEvent().getName(),event.getEvent());
        return commonMapper.addEventInTrack(trackNames,event.getEvent().getName());

    }

    public Object getEvent(String eventName){

        if(!eventDetails.containsKey(eventName) || eventDetails.get(eventName).getCount()<1){
            return Response.builder().Error(ErrorCodes.EVENT_DOES_NOT_EXISTS.getErrorMessage())
                    .ErrorCode(ErrorCodes.EVENT_DOES_NOT_EXISTS.getErrorCode()).build();
        }

        return eventDetails.get(eventName);

    }

    public Response updateEvent(Event event){

        if(!eventDetails.containsKey(event.getName()) || eventDetails.get(event.getName()).getCount()<1){
            return Response.builder().Error(ErrorCodes.EVENT_DOES_NOT_EXISTS.getErrorMessage())
                    .ErrorCode(ErrorCodes.EVENT_DOES_NOT_EXISTS.getErrorCode()).build();
        }

        int currCount  = eventDetails.get(event.getName()).getCount();
        event.setCount(currCount);
        eventDetails.put(event.getName(), event);

        return Response.builder().message(Constants.EVENT_UPDATED).build();

    }


}
