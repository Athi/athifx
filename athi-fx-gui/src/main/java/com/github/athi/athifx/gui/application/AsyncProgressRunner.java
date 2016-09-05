package com.github.athi.athifx.gui.application;

import javafx.concurrent.Task;

/**
 * Created by Athi
 */
public class AsyncProgressRunner extends Task<Object> {

    private final WaitingScreen waitingScreen;

    private Runnable runnable;

    private AsyncProgressRunner(Runnable runnable) {
        this.runnable = runnable;
        waitingScreen = new WaitingScreen();
        waitingScreen.show(this);
    }

    public static void buildAndRun(Runnable runnable) {
        AsyncProgressRunner asyncProgressRunner = new AsyncProgressRunner(runnable);
        new Thread(asyncProgressRunner).start();
    }

    @Override
    protected Object call() throws Exception {
        runnable.run();
        return null;
    }

    @Override
    protected void succeeded() {
        super.succeeded();
        waitingScreen.close();
    }
}
