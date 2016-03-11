package org.athifx.injector.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created by Athi on 2016-03-11.
 */
public class EventBusSubscriptionListener {

    @Subscribe
    public void thirdSubscribe(EventBusEvent eventBusEvent) {
        System.out.println(eventBusEvent.getMessage());
    }
}
