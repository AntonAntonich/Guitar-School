package test.com.anton.gs.model.util.impl;

import com.anton.gs.model.util.impl.UrlModifierImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UrlModifierImplTest {

    @Test
    public void testModifyUrlPositive() {
        String actual = UrlModifierImpl.getInstance().modifyUrl("http://localhost:8080/Guitar_School_0_0_2_war_exploded/jsp/header.jsp");
        String expected = "/jsp/header.jsp";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testModifyUrlNegative() {
        String actual = UrlModifierImpl.getInstance().modifyUrl("http://localhost:8080/Guitar_School_0_0_2_war_exploded/jsp/header.jsp");
        String expected = "jsp/header.jsp";
        Assert.assertNotEquals(actual, expected);
    }
}