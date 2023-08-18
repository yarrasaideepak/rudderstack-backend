package com.rudderstack.backend.Events.services;

import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.beans.Event;
import com.rudderstack.backend.common.beans.Response;

public interface EventService {

    Response addEvent(AddEventBean event);
    Object getEvent(String eventName);
    Response updateEvent(Event event);

}
