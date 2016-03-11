package org.athifx.injector.eventbus;

/**
 * Created by Athi
 */
public class EventBusEvent {

    private final String message;

    public EventBusEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
