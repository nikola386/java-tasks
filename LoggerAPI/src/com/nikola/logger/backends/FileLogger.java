package com.nikola.logger.backends;

import com.nikola.logger.LogLevel;
import com.nikola.logger.config.LoggerConfig;
import com.nikola.logger.config.LoggerConfigFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger implements LogBackend {
    private static final LogLevel DEFAULT_LEVEL = LogLevel.WARNING;
    private static final String DEFAULT_LOG_PATH = "./logger.log";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss:SSS");
    private static PrintStream printStream;

    private final String className;
    private final LogLevel level;

    static {
        LoggerConfig config = LoggerConfigFactory.getConfig();
        String logPath = config.getFilePath();
        if(logPath == null) {
            logPath = DEFAULT_LOG_PATH;
        }
        openLogFile(logPath);
    }

    public FileLogger(String className) {
        this.className = className;
        LogLevel level;

        String levelName = LoggerConfigFactory.getConfig().getLevel();
        if (levelName == null) {
            level = DEFAULT_LEVEL;
        } else {
            LogLevel matchedLevel;
            try {
                matchedLevel = LogLevel.valueOf(levelName.toUpperCase());
            } catch (IllegalArgumentException iae) {
                throw new IllegalArgumentException("Level '" + levelName + "' was not found", iae);
            }
            level = matchedLevel;
        }

        this.level = level;
    }

    public static void openLogFile(String logPath) {
        if (logPath == null) {
            printStream = System.out;
        } else {
            try {
                printStream = new PrintStream(new FileOutputStream(logPath, true));
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("Log file " + logPath + " was not found", e);
            }
        }
    }

    private void printMessage(LogLevel level, String message, Throwable t) {
        if (!isLevelEnabled(level)) {
            return;
        }
        StringBuilder sb = new StringBuilder(128);
        DateFormat dateFormat = (DateFormat) DATE_FORMAT.clone();
        sb.append(dateFormat.format(new Date()));
        sb.append(" : ").append(level.name()).append(" : ");
        sb.append(className).append(" : ");
        sb.append("METHOD_NAME").append(" : ");
        sb.append(message);
        printStream.print(sb);
        if (t != null) {
            printStream.print(" : ");
            t.printStackTrace(printStream);
        } else {
            printStream.println();
        }
    }

    @Override
    public boolean isLevelEnabled(LogLevel level) {
        return this.level.isEnabled(level);
    }

    @Override
    public void log(LogLevel level, String message) {
        printMessage(level, message, null);
    }

    @Override
    public void log(LogLevel level, String message, Throwable t) {
        printMessage(level, message, t);
    }

    public static class FileLoggerFactory implements LogBackendFactory {
        public FileLoggerFactory() {
        }

        @Override
        public LogBackend createLogBackend(String classLabel) {
            return new FileLogger(classLabel);
        }
    }
}
