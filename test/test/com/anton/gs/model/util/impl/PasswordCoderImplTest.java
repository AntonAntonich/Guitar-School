package test.com.anton.gs.model.util.impl;

import com.anton.gs.model.util.impl.PasswordCoderImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordCoderImplTest {

    @Test
    public void testCodePasswordPositive() {
        String actual = PasswordCoderImpl.getInstance().codePassword("aaaAAA333#");
        String expected = "524fab9770198b17667f350a6bb2db3201de8db7";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCodePasswordNegative() {
        String actual = PasswordCoderImpl.getInstance().codePassword("aaaAAA333#");
        String expected = "524fab9770198b17667f350a6bb2db3201de8db";
        Assert.assertNotEquals(actual, expected);
    }
}