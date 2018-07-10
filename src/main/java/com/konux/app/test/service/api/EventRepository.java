package com.konux.app.test.service.api;

public interface EventRepository {

    /**
     * Persists Event
     *
     * @param event
     */
    void save(Event event);

}
