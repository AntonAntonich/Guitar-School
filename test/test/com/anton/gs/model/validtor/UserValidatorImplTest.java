package test.com.anton.gs.model.validtor;

import com.anton.gs.controller.command.MessageManager;
import com.anton.gs.controller.command.MessagePath;
import com.anton.gs.model.validator.impl.UserValidatorImpl;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserValidatorImplTest {
    UserValidatorImpl userValidator;
    @BeforeClass
    public void setUp(){
       userValidator = UserValidatorImpl.getInstance();
    }

    @AfterClass
    public void tearDown() {
        userValidator = null;
    }

    @Test
    public void testValidateFormEmailConstraintPositive() {
        String actual = userValidator.validateForm(
                "aaa@gmail.com",
                "aaaAAA333#",
                "aaaAAA333#",
                "anton"
        ).get("errorEmailMessage");

        String expected = MessageManager.getProperty(MessagePath.EMAIL_CONSTRAINT_PATH);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testValidateFormPasswordConstraintPositive() {
        String actual = userValidator.validateForm(
                "aaa@gmail.com",
                "aaaAAA33#",
                "aaaAAA333#",
                "anton"
        ).get("errorPasswordMessage");

        String expected = "пароль должен содержать как минимум\n" +
                ":одну латинску букву в ВЕРХНЕМ регитсре,одну латинскую букву в НИЖНЕМ регистре, один из сиволов !@#$%^&*,  одну цифру";
        Assert.assertEquals(actual, expected);
        System.out.println(actual);
    }
}