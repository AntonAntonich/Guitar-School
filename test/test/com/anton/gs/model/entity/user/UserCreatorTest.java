package test.com.anton.gs.model.entity.user;

import com.anton.gs.model.entity.user.User;
import com.anton.gs.model.entity.user.UserCreator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UserCreatorTest {
    UserCreator userCreator;

    @BeforeClass
    public void setUp(){
       userCreator =  UserCreator.getInstance();
    }

    @Test
    public void testCreateUserPositive() {
        User actual = userCreator.createUser(2,
                "aaa@gmail.com",
                "vasa",
                "tutor",
                "senior",
                "start",
                false);
        User expected = userCreator.createUser(2,
                "aaa@gmail.com",
                "vasa",
                "tutor",
                "senior",
                "start",
                false);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testCreateUserNegative() {
        User actual = userCreator.createUser(2,
                "aaa@gmail.com",
                "vasa",
                "tutor",
                "senior",
                "start",
                false);
        User expected = userCreator.createUser(1,
                "aaa@gmail.com",
                "vasa",
                "tutor",
                "senior",
                "start",
                false);
        Assert.assertNotEquals(actual, expected);
    }

    @AfterClass
    public void tierDown(){
        userCreator = null;
    }
}