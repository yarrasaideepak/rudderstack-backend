package com.rudderstack.backend.Tracking.services;

import com.rudderstack.backend.Tracking.beans.RequestStatusBean;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;

import java.util.List;

public interface TrackingService {

    RequestStatusBean addTrack(TrackingPlanBean trackingPlanBean);
    List<TrackingPlanBean> getTracking();
    RequestStatusBean updateTracking(TrackingPlanBean trackingPlanBean);

}
