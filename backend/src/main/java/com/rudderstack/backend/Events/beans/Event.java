package com.rudderstack.backend.Events.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.rudderstack.backend.common.beans.Response;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Event extends Response {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("rules")
    private RulesForObjects rules;

    @JsonIgnore
    private int count = 0;

    @Getter
    @Setter
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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
