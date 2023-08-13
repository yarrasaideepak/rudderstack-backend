package com.rudderstack.backend.Tracking.beans;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestStatusBean {

    private String message;
    private String Error;
    private String ErrorCode;

}
