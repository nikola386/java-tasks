package com.nikola.logger;

import com.nikola.logger.backends.FileLogger;
import com.nikola.logger.backends.LogBackendFactory;

public class LoggerFactory {
    private static LogBackendFactory logBackendFactory;

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> c) {
        return getLogger(c.getName());
    }

    public static Logger getLogger(String className) {
        if (logBackendFactory == null) {
            logBackendFactory = new FileLogger.FileLoggerFactory();

        }
        return new Logger(logBackendFactory.createLogBackend(className));
    }
}
