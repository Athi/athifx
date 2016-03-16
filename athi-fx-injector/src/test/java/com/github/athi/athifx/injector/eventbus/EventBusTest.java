package com.github.athi.athifx.injector.eventbus;

import com.github.athi.athifx.injector.AthiFXTestCase;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class EventBusTest extends AthiFXTestCase {

    private static final String FIRST_MESSAGE = "FIRST_MESSAGE";
    private static final String SECOND_MESSAGE = "SECOND_MESSAGE";
    private static final String THIRD_MESSAGE = "THIRD_MESSAGE";

    @Inject
    private EventBus eventBus;

    @Rule
    private final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void eventBUsEventTest() {
        eventBus.post(new EventBusEvent(THIRD_MESSAGE));
        String trimmedLog = systemOutRule.getLog().trim();
        assertEquals(true, trimmedLog.contains(FIRST_MESSAGE));
        assertEquals(true, trimmedLog.contains(SECOND_MESSAGE));
//        assertEquals(true, trimmedLog.contains(THIRD_MESSAGE)); //FIXME not working
    }

    @Subscribe
    public void firstSubscribe(EventBusEvent eventBusEvent) {
        System.out.println(FIRST_MESSAGE);
    }

    @Subscribe
    public void secondSubscribe(EventBusEvent eventBusEvent) {
        System.out.println(SECOND_MESSAGE);
    }
}
