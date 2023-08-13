package com.rudderstack.backend.Events.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rudderstack.backend.Tracking.beans.TrackingPlanBean;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AddEventBean {

    @JsonProperty("tracks")
    List<String> tracks;
    @JsonProperty("event")
    TrackingPlanBean.Tracking_Plan.Rules.Events event;

}
