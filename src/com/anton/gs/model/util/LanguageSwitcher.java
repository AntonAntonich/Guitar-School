package com.anton.gs.model.util;

import java.util.Locale;

/**
 * The interface Language switcher.
 */
public interface LanguageSwitcher {
     /**
      * Change language locale.
      *
      *
      * @param currentLocale the current locale
      * @param targetLocale  the target locale
      * @return the locale
      */
     Locale changeLanguage(Locale currentLocale, String targetLocale);
}
