package com.nikola.logger.backends;

import com.nikola.logger.LogLevel;

public interface LogBackend {
    boolean isLevelEnabled(LogLevel level);

    void log(LogLevel level, String message);

    void log(LogLevel level, String message, Throwable throwable);
}