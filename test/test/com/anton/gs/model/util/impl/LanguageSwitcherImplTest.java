package test.com.anton.gs.model.util.impl;

import com.anton.gs.model.util.impl.LanguageSwitcherImpl;
import org.testng.Assert;

import java.util.Locale;

import static org.testng.Assert.*;

public class LanguageSwitcherImplTest {

    @org.testng.annotations.Test
    public void testChangeLanguagePositive() {
        LanguageSwitcherImpl languageSwitcher = LanguageSwitcherImpl.getInstance();
        Locale actual = languageSwitcher.changeLanguage(new Locale("ru", "RU"), "en");
        Locale expected = new Locale("en", "US");
        Assert.assertEquals(actual, expected);
    }

    @org.testng.annotations.Test
    public void testChangeLanguageNegative() {
        LanguageSwitcherImpl languageSwitcher = LanguageSwitcherImpl.getInstance();
        Locale actual = languageSwitcher.changeLanguage(new Locale("ru", "RU"), "en");
        Locale expected = Locale.FRANCE;
        assertNotEquals(expected, actual);
    }
}