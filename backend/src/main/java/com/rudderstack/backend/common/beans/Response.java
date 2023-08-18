package com.rudderstack.backend.common.beans;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {

    private String message;
    private String Error;
    private String ErrorCode;

}
