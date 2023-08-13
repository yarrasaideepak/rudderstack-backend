package com.rudderstack.backend.Events.services.impl;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.services.EventService;
import com.rudderstack.backend.Tracking.beans.RequestStatusBean;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;
import com.rudderstack.backend.Tracking.services.TrackingService;
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

    //Due to time constraints, we're not using these for a database in the project.
    // I hope you understand.

    private HashMap<String,List<String>> event_to_tracks = new HashMap<>();
    private List<TrackingPlanBean.Tracking_Plan.Rules.Events> allEvents = new ArrayList<>();

    public RequestStatusBean addEvent(AddEventBean event){

        if(event_to_tracks.containsKey(event.getEvent().getName())){
            return RequestStatusBean.builder().ErrorCode("Err003").Error("This event is already present").build();
        }

        allEvents.add(event.getEvent());

        event_to_tracks.put(event.getEvent().getName(),event.getTracks());

        HashMap<String,TrackingPlanBean> trackingDetails = trackingService.getTrackingDetails();

        for(String trackNames: event.getTracks()) {
            for(Map.Entry<String,TrackingPlanBean> mapElement : trackingDetails.entrySet()) {
                    if (Objects.equals(trackNames, mapElement.getValue().getTracking_plan().getDisplay_name())) {
                        mapElement.getValue().getTracking_plan().getRules().getEvents().add(event.getEvent());
                    }
            }
        }

        trackingService.setTrackingDetails(trackingDetails);

        return RequestStatusBean.builder().message("Events Added Successfully").build();

    }

    public List<TrackingPlanBean.Tracking_Plan.Rules.Events> getEvent(){

        return allEvents;

    }

    public RequestStatusBean updateEvent(TrackingPlanBean.Tracking_Plan.Rules.Events event){

        if(!event_to_tracks.containsKey(event.getName())){
            return RequestStatusBean.builder().ErrorCode("Err004").Error("This event Does not exist to update").build();
        }

        List<TrackingPlanBean> tracks= trackingService.getTracking();

        for(String trackName: event_to_tracks.get(event.getName())){

            for(TrackingPlanBean trackBean: tracks){
                if(Objects.equals(trackBean.getTracking_plan().getDisplay_name(), trackName)){
                    for(int i=0;i<trackBean.getTracking_plan().getRules().getEvents().size();i++){
                        if(Objects.equals(trackBean.getTracking_plan().getRules().getEvents().get(i).getName(), event.getName())){
                            trackBean.getTracking_plan().getRules().getEvents().set(i,event);
                            trackingService.updateTracking(trackBean);
                            break;
                        }
                    }
                }
            }

        }

        return RequestStatusBean.builder().message("Update is Successful").build();

    }


}
