package com.nikola.logger;

import com.nikola.logger.backends.LogBackend;

public class Logger {
    private final LogBackend backend;

    public Logger(LogBackend backend) {
        this.backend = backend;
    }

    public void severe(String message) {
        logIfEnabled(LogLevel.SEVERE, message, null);
    }

    public void severe(String message, Throwable t) {
        logIfEnabled(LogLevel.SEVERE, message, t);
    }

    public void warning(String message) {
        logIfEnabled(LogLevel.WARNING, message, null);
    }

    public void warning(String message, Throwable t) {
        logIfEnabled(LogLevel.WARNING, message, t);
    }

    public void info(String message) {
        logIfEnabled(LogLevel.INFO, message, null);
    }

    public void config(String message) {
        logIfEnabled(LogLevel.CONFIG, message, null);
    }

    public void fine(String message) {
        logIfEnabled(LogLevel.FINE, message, null);
    }

    public void finer(String message) {
        logIfEnabled(LogLevel.FINER, message, null);
    }

    public void finest(String message) {
        logIfEnabled(LogLevel.FINEST, message, null);
    }

    protected void logIfEnabled(LogLevel level, String msg, Throwable throwable) {
        if (backend.isLevelEnabled(level)) {
            if (throwable == null) {
                backend.log(level, msg);
            } else {
                backend.log(level, msg, throwable);
            }
        }
    }

}
