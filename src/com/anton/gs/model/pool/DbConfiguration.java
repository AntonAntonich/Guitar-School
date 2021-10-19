package com.anton.gs.model.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

final class DbConfiguration {
    private static Logger logger = LogManager.getLogger();

    static final String DB_NAME;
    static final String DB_URL;
    static final String DB_LOGIN;
    static final String DB_PASSWORD;
    static final int DB_CONNECTION_POOL_SIZE;
    static final String DB_PROPERTIES_FILE = "db";
    static ResourceBundle resourceBundle;

    static {
        try {
            resourceBundle = ResourceBundle.getBundle(DB_PROPERTIES_FILE);
            DB_NAME = resourceBundle.getString("db.name");
            DB_URL = resourceBundle.getString("db.url");
            DB_LOGIN = resourceBundle.getString("db.login");
            DB_PASSWORD = resourceBundle.getString("db.password");
            DB_CONNECTION_POOL_SIZE = Integer.parseInt(resourceBundle.getString("db.pool_size"));
        } catch (MissingResourceException e) {
            logger.log(Level.ERROR, "couldn't find resources: " + DB_PROPERTIES_FILE);
            throw new ExceptionInInitializerError(e);
        }
    }

    private DbConfiguration() {
    }
}
