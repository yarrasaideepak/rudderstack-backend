package com.rudderstack.backend.Events.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rudderstack.backend.Events.beans.AddEventBean;
import com.rudderstack.backend.Events.services.impl.EventServiceImpl;
import com.rudderstack.backend.common.Constants.ErrorCodes;
import com.rudderstack.backend.common.beans.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class TestEventServiceImpl {

    @Autowired
    EventServiceImpl eventService;

    @Test
    public void testAddEvent() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        AddEventBean addEventBean = objectMapper.readValue(new File("src\\test\\resources\\Events.json"), AddEventBean.class);

        Response response = eventService.addEvent(addEventBean);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorMessage(),response.getError());
        Assertions.assertEquals(ErrorCodes.TRACK_DOEST_NOT_EXIST.getErrorCode(),response.getErrorCode());

    }

}
