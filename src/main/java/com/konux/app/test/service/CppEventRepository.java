package com.konux.app.test.service;

import com.konux.app.test.proto.Event.UserEvent;
import com.konux.app.test.service.api.Event;
import com.konux.app.test.service.api.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.konux.app.test.service.EventFactory.toProtoEvent;

/**
 * Serializes event to protobuf and sends it over HTTP to the c++ storage service.
 */
@Component
public class CppEventRepository implements EventRepository {

    @Value("${test.app.persistence.service.url}")
    private String url;

    private HttpClient httpClient;

    @Autowired
    public CppEventRepository(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void save(final Event event) {
        UserEvent protoMessage = toProtoEvent(event);
        httpClient.doPost(url, protoMessage.toByteArray());
    }


    public void setUrl(final String url) {
        this.url = url;
    }
}
