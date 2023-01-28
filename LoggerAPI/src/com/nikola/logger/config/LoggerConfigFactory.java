package com.nikola.logger.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoggerConfigFactory {
    private static final String LOG_LEVEL = "log.level";
    private static final String LOG_FILE = "log.file";
    private static final String PROPERTIES_FILE = "logger.properties";

    public static LoggerConfig getConfig() {
        LoggerConfig config = new LoggerConfig();
        Properties prop = new Properties();

        try (InputStream inputStream = LoggerConfigFactory.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if(inputStream != null) {
                prop.load(inputStream);

                config.setLevel(prop.getProperty(LOG_LEVEL));
                config.setFilePath(prop.getProperty(LOG_FILE));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return config;
    }
}
