package com.rudderstack.backend.Events.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rudderstack.backend.Tracking.beans.TrackingPlan;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddEventBean {

    @JsonProperty("tracks")
    List<String> tracks;
    @JsonProperty("event")
    Event event;

}
