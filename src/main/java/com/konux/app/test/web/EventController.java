package com.konux.app.test.web;

import com.konux.app.test.service.api.Event;
import com.konux.app.test.service.api.EventRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Event resource", description = "Manage events")
public class EventController {

    public static final Logger log = Logger.getLogger(EventController.class);

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/event", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Persist event")
    public Event addEvent(@RequestBody Event event) {
        log.info("incoming event:\n\n" + event);

        eventRepository.save(event);

        return event;
    }

}
