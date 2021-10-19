package com.anton.gs.controller.command.impl;
import com.anton.gs.controller.command.*;
import com.anton.gs.model.util.impl.LanguageSwitcherImpl;
import com.anton.gs.model.util.impl.UrlModifierImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;


public class ChangeLanguageCommand implements Command {
    private static Logger logger = LogManager.getLogger();

    private static final String PREVIOUS_PAGE = "Referer";
    private static final String RU_COUNTRY = "Ru";
    private static final String RU_LANG = "ru";

    @Override
    public Router execute(HttpServletRequest req) {
        Locale currentLocale = (Locale) req.getSession().getAttribute(SessionAttribute.LOCALE);
        currentLocale = checkLocale(currentLocale);
        String targetLanguage = req.getParameter(RequestParam.VALUE);
        Locale locale = LanguageSwitcherImpl.getInstance().changeLanguage(currentLocale, targetLanguage);
        MessageManager.setDefaultLocale(locale);
        req.getSession().setAttribute(SessionAttribute.LOCALE, locale);
        String url = req.getParameter(RequestParam.CURRENT_PATH);
        url = UrlModifierImpl.getInstance().modifyUrl(url);
        Router router = new Router();
        router.setTypeForward();
        router.setPage(url);
        return router;
    }

    private Locale checkLocale(Locale locale) {
        return (locale == null) ? new Locale(RU_LANG, RU_COUNTRY) : locale;
    }
}

