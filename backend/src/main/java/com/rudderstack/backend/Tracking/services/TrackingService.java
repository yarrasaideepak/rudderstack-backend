package com.rudderstack.backend.Tracking.services;

import com.rudderstack.backend.common.beans.Response;
import com.rudderstack.backend.Tracking.beans.TrackingPlan;

public interface TrackingService {

    Response addTrack(TrackingPlan trackingPlanBean);
    Object getTracking(String trackName);
    Response updateTracking(TrackingPlan trackingPlan);

}
