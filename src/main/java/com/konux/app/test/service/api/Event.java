package com.konux.app.test.service.api;

public class Event {

    private String event;
    private int userId;
    private int timestamp;

    public static Event with(String event, int userId, int timestamp) {
        Event newEvent = new Event();
        newEvent.event =event;
        newEvent.timestamp = timestamp;
        newEvent.userId = userId;
        return newEvent;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(final String event) {
        this.event = event;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(final int userId) {
        this.userId = userId;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Event{" + "event='" + event + '\'' + ", userId=" + userId + ", timestamp=" + timestamp + '}' + "\n";
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Event event1 = (Event) o;

        if (userId != event1.userId) return false;
        if (timestamp != event1.timestamp) return false;
        return event != null ? event.equals(event1.event) : event1.event == null;
    }

    @Override
    public int hashCode() {
        int result = event != null ? event.hashCode() : 0;
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }
}
