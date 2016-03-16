package com.github.athi.athifx.injector.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * Created by Athi
 */
class EventBusSubscriptionListener {

    @Subscribe
    public void thirdSubscribe(EventBusEvent eventBusEvent) {
        System.out.println(eventBusEvent.getMessage());
    }
}
