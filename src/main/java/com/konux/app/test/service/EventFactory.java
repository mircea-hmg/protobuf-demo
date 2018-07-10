package com.konux.app.test.service;

import com.konux.app.test.proto.Event.UserEvent;
import com.konux.app.test.service.api.Event;

public class EventFactory {

    private EventFactory() {}

    public static UserEvent toProtoEvent(final Event event) {
        return UserEvent.newBuilder()
                        .setEvent(event.getEvent())
                        .setUserId(event.getUserId())
                        .setTimestamp(event.getTimestamp())
                        .build();
    }
}
