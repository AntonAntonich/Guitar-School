package com.anton.gs.controller.command;

import java.util.ResourceBundle;

public class PageManager {
    private static final String PAGES_NAMES  = "pages";
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle(PAGES_NAMES);
    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }
}
