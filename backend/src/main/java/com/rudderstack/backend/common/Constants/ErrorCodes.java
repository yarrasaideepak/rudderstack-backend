package com.rudderstack.backend.common.Constants;

import com.rudderstack.backend.Tracking.beans.TrackingPlan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCodes {

    EVENT_ALREADY_EXISTS("Event with given Name already Exists","REE001"),

    EVENT_DOES_NOT_EXISTS("Event with given Name doesn't exist","REE002"),

    EVENT_ALREADY_EXISTS_NOT_EQUAL("Event provided already exists and is not same as existing Event","REE003"),

    TRACK_DOEST_NOT_EXIST("There is no Track with given Name","RTE001"),

    TRACK_WITH_SAME_DISPLAY_EXISTS("Tracking Plan with same Display Name exists","RTE002");



    String errorMessage;
    String errorCode;

}
