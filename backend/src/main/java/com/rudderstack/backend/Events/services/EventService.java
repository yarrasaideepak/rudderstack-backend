package com.rudderstack.backend.Events.services;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Tracking.beans.RequestStatusBean;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;

import java.util.List;

public interface EventService {

    RequestStatusBean addEvent(AddEventBean event);
    List<TrackingPlanBean.Tracking_Plan.Rules.Events> getEvent();
    RequestStatusBean updateEvent(TrackingPlanBean.Tracking_Plan.Rules.Events event);

}
