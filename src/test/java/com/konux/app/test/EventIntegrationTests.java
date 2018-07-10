package com.konux.app.test;

import com.google.common.collect.Lists;
import com.konux.app.test.service.api.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class EventIntegrationTests {

    @Configuration
    public static class RestClientConfiguration {
        @Bean
        RestTemplate restTemplate() {
            return new RestTemplate(Lists.newArrayList(
                new MappingJackson2HttpMessageConverter() {
            }));
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    private final String url = "http://127.0.0.1:8080/collector/event";

    @Test
    public void shouldProcessSubmittedEvent() {
        Event event = Event.with("trigger description", 1, 2);

        ResponseEntity<Event> response = restTemplate.postForEntity(url, event, Event.class);

        assertEquals("api should return parsed event",
                     event, response.getBody());
    }

}
