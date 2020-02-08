package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.github.athi.athifx.gui.configuration.ApplicationConfigurationException;
import com.github.athi.athifx.gui.navigation.view.View;
import com.github.athi.athifx.gui.notification.Notification;
import com.github.athi.athifx.gui.security.Security;
import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.github.athi.athifx.injector.log.Log;
import com.google.common.collect.LinkedHashMultiset;
import org.reflections8.Reflections;

import javax.enterprise.inject.Instance;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE;

/**
 * Created by Athi
 */
class ApplicationConfigurationUtils {

    private static final Log LOGGER = Log.getLogger(AthiFXApplication.class);

    static void initUncaughtExceptionHandler() {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            LOGGER.error(throwable.getMessage(), throwable);
            Notification.error(UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE, throwable.getMessage());
        });
    }

    static void initDefaultApplicationConfiguration() {
        try {
            Reflections reflections = AthiFXInjector.getReflections();
            Set<Class<? extends ApplicationConfiguration>> configuration = reflections.getSubTypesOf(ApplicationConfiguration.class);
            if (configuration.size() == 1) {
                configuration.iterator().next().newInstance().init();
            } else if (configuration.isEmpty()) {
                throw new ApplicationConfigurationException("No application configuration implementations!!");
            } else {
                throw new ApplicationConfigurationException("Too many application configuration implementations!!");
            }
        } catch (Exception e) {
            throw new ApplicationConfigurationException("Application configuration exception: " + e.getMessage(), e);
        }
    }

    static void validateSecurityDefinition(Instance<Security> securityInstance) {
        Iterator<Security> iterator = securityInstance.iterator();
        if (iterator.hasNext()) {
            iterator.next();
            if (iterator.hasNext()) {
                throw new ApplicationConfigurationException("Too many Security implementations!!");
            }
        }
    }

    static void validateViewDefinitions() {
        Reflections reflections = AthiFXInjector.getReflections();
        List<Long> itemIds = reflections.getTypesAnnotatedWith(View.class)
                .stream()
                .map(type -> type.getAnnotation(View.class))
                .map(View::itemId)
                .collect(Collectors.toList());

        LinkedHashMultiset<Long> itemIdsMultiset = LinkedHashMultiset.create(itemIds);
        itemIdsMultiset.entrySet().removeIf(entry -> entry.getCount() == 1);
        Set<Long> duplicates = itemIdsMultiset.elementSet();

        if (!duplicates.isEmpty()) {
            throw new ApplicationConfigurationException("Duplicate View id's: " + duplicates);
        }
    }

}
