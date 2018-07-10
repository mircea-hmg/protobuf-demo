package com.konux.app.test.service;

import com.konux.app.test.proto.Event.UserEvent;
import com.konux.app.test.service.api.Event;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.konux.app.test.service.EventFactory.toProtoEvent;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CppEventRepositoryTest {

    private final String url = "http://localhost";

    @Mock
    private HttpClient httpClient;

    private CppEventRepository repository;
    private Event event;
    private UserEvent protoEvent;

    @Before
    public void setUp() throws Exception {
        repository = new CppEventRepository(httpClient);
        repository.setUrl(url);
        event = Event.with("description", 1, 2);
        protoEvent = toProtoEvent(event);
    }

    @Test
    public void should_SerializeToProtobuf_andMakePostRequest() throws Exception {
        given(httpClient.doPost(url, protoEvent.toByteArray()))
            .willReturn(HttpStatus.SC_OK);

        // when
        repository.save(event);

        // then
        verify(httpClient).doPost(url, protoEvent.toByteArray());
    }

}