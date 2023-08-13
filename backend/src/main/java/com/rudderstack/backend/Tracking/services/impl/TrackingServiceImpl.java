package com.rudderstack.backend.Tracking.services.impl;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.services.impl.EventServiceImpl;
import com.rudderstack.backend.Tracking.beans.RequestStatusBean;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;
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

    //Due to time constraints,I am not going with database in the project.
    // I hope you understand.
    private HashMap<String,TrackingPlanBean> trackingDetails = new HashMap<>();

    public RequestStatusBean addTrack(TrackingPlanBean trackingPlanBean){

        if(trackingDetails.containsKey(trackingPlanBean.getTracking_plan().getDisplay_name())){
            return RequestStatusBean.builder().Error("Tracking Plan with same Display name exists").ErrorCode("Err001").build();
        }
        trackingDetails.put(trackingPlanBean.getTracking_plan().getDisplay_name(),trackingPlanBean);

        HashMap<String,List<String>> event_to_tracks = eventService.getEvent_to_tracks();
        for(TrackingPlanBean.Tracking_Plan.Rules.Events event: trackingPlanBean.getTracking_plan().getRules().getEvents()){
            List<String> trackName = new ArrayList<>();
            trackName.add(trackingPlanBean.getTracking_plan().getDisplay_name());
            event_to_tracks.put(event.getName(),trackName);
        }
        eventService.setEvent_to_tracks(event_to_tracks);

        return RequestStatusBean.builder().message("Tracking added Successfully").build();
    }

    public List<TrackingPlanBean> getTracking(){
        List<TrackingPlanBean> trackingPlanBeanList = new ArrayList<>();

        for (Map.Entry<String,TrackingPlanBean> mapElement : trackingDetails.entrySet()) {
            trackingPlanBeanList.add(mapElement.getValue());
        }

        return trackingPlanBeanList;
    }

    public RequestStatusBean updateTracking(TrackingPlanBean trackingPlanBean){
        if(!trackingDetails.containsKey(trackingPlanBean.getTracking_plan().getDisplay_name())){
            return RequestStatusBean.builder().Error("There is no Tracking plan with provided display name").ErrorCode("Err002").build();
        }
        trackingDetails.put(trackingPlanBean.getTracking_plan().getDisplay_name(),trackingPlanBean);

        return RequestStatusBean.builder().message("Tracking Update Successfully").build();
    }

}
