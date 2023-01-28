package com.nikola.logger.backends;

public interface LogBackendFactory {
    LogBackend createLogBackend(String classLabel);
}
