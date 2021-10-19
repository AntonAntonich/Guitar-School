package com.anton.gs.model.util.impl;

import com.anton.gs.model.util.LanguageSwitcher;

import java.util.Locale;

public final class LanguageSwitcherImpl implements LanguageSwitcher {
    private static final String LANG_RU = "ru";
    private static final String LANG_EN = "en";
    private static final String COUNTRY_RU = "RU";
    private static final String COUNTRY_US = "US";

    private static LanguageSwitcherImpl languageSwitcher;

    private LanguageSwitcherImpl() {
    }

    public static LanguageSwitcherImpl getInstance() {
        if(languageSwitcher == null) {
            languageSwitcher = new LanguageSwitcherImpl();
        }
        return languageSwitcher;
    }

    @Override
    public  Locale changeLanguage(Locale currentLocale, String targetLocale) {
        Locale locale = null;
        if(!currentLocale.getLanguage().equals(targetLocale)){
            switch (targetLocale){
                case LANG_RU:
                    locale = new Locale(LANG_RU, COUNTRY_RU);

                    break;
                case LANG_EN:
                    locale = new Locale(LANG_EN, COUNTRY_US);
                    break;
            }
        } else {
            locale = currentLocale;
        }
        return locale;
    }
}
