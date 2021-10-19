package com.anton.gs.model.util;

import java.util.ResourceBundle;

public final class ConfigurationManager {
    private static final String PAGES_NAMES  = "pages";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(PAGES_NAMES);

    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
