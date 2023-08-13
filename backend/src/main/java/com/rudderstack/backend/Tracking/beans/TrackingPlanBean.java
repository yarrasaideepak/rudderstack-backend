package com.rudderstack.backend.Tracking.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
public class TrackingPlanBean {

    private Tracking_Plan tracking_plan;

    @Getter
    @Setter
    public static class Tracking_Plan{

        @JsonProperty("display_name")
        private String display_name;
        @JsonProperty("rules")
        private Rules rules;

        @Getter
        @Setter
        public static class Rules{
            @JsonProperty("events")
            private List<Events> events;

            @Getter
            @Setter
            public static class Events{

                @JsonProperty("name")
                private String name;
                @JsonProperty("description")
                private String description;
                @JsonProperty("rules")
                private RulesForObjects rules;

                @Getter
                @Setter
                public static class RulesForObjects{
                    @JsonProperty("$schema")
                    private String $schema;
                    @JsonProperty("type")
                    private String type;
                    @JsonProperty("properties")
                    private Map<String, Object> properties;
                    @JsonProperty("required")
                    private String[] required;

                }

            }

        }

    }

}
