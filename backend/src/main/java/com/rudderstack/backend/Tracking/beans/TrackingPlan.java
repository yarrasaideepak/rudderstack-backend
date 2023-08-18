package com.rudderstack.backend.Tracking.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rudderstack.backend.Events.beans.Event;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TrackingPlan {

    private Tracking_Plan tracking_plan;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public static class Tracking_Plan{

        @JsonProperty("display_name")
        private String display_name;
        @JsonProperty("rules")
        private Rules rules;

        @Getter
        @Setter
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        public static class Rules{
            private List<String> eventNames;
            @JsonProperty("events")
            private List<Event> events;

        }

    }

}
