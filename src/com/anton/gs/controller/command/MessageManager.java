package com.anton.gs.controller.command;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private static final String MESSAGES  = "message";
    private static  ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES);

    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }

    public static void setDefaultLocale(Locale locale) {
        Locale.setDefault(locale);
        resourceBundle = ResourceBundle.getBundle(MESSAGES, locale);
    }
}

