package org.athifx.injector.eventbus;

/**
 * Created by Athi
 */
class EventBusEvent {

    private final String message;

    public EventBusEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
