package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.menu.item.DisableEnableAMenuItemEvent;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.security.Security;
import com.github.athi.athifx.injector.log.Log;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;

import static com.github.athi.athifx.gui.application.ApplicationConfigurationUtils.*;
import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.INJECTOR_CONFIGURATION;
import static com.github.athi.athifx.injector.injection.AthiFXInjector.createInjector;

/**
 * Created by Athi
 */
public class AthiFXApplication extends Application {

    private static final Log LOGGER = Log.getLogger(AthiFXApplication.class);

    @Inject
    private AthiFXApplicationProperties applicationProperties;

    @Inject
    private Navigator navigator;

    @Inject
    @Any
    private Instance<Security> securityInstance;

    private LoadingScreen loadingScreen;

    @Inject
    private LoginScreen loginScreen;

    @Inject
    private MainScreen mainScreen;

    @Inject
    private EventBus eventBus;

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AthiFXApplication.primaryStage = primaryStage;

        initDefaultApplicationConfiguration();
        initUncaughtExceptionHandler();

        loadingScreen = new LoadingScreen();
        loadingScreen.show();

        new Thread(() -> {
            try {
                createInjector(this, INJECTOR_CONFIGURATION);
                validateSecurityDefinition(securityInstance);
                validateViewDefinitions();
                showApplication();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    LOGGER.error(e.getMessage(), e);
                    loadingScreen.setError(ErrorParser.parse(e));
                });
            }
        }).start();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private void showApplication() {
        if (securityInstance.iterator().hasNext()) {
            withSecurity();
        } else {
            withoutSecurity();
        }
    }

    private void withoutSecurity() {
        Platform.runLater(() -> {
            loadingScreen.close();
            showMainScreen();
            navigateToFirstItem();
        });
    }

    private void withSecurity() {
        Platform.runLater(() -> {
            loadingScreen.close();
            loginScreen.show(() -> {
                showMainScreen();
                disableAMenuItemsWithoutPermission();
                navigateToFirstItem();
            });
        });
    }

    private void showMainScreen() {
        mainScreen.show(primaryStage);
    }

    private void navigateToFirstItem() {
        navigator.navigateTo(applicationProperties.getItems().get(0));
    }

    private void disableAMenuItemsWithoutPermission() {
        applicationProperties.getItems().stream()
                .filter(item -> !applicationProperties.isNotSecured(item))
                .forEach(item -> eventBus.post(new DisableEnableAMenuItemEvent(item)));
    }
}
