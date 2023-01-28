package com.nikola.logger;

public enum LogLevel {
    SEVERE(7),
    WARNING(6),
    INFO(5),
    CONFIG(4),
    FINE(3),
    FINER(2),
    FINEST(1);

    private final int intLevel;

    LogLevel(final int val) {
        intLevel = val;
    }

    public int getItLevel() {
        return intLevel;
    }

    public boolean isEnabled(LogLevel otherLevel) {
        return (intLevel <= otherLevel.getItLevel());
    }
}
