package test.com.anton.gs.model.entity.order;

import com.anton.gs.model.entity.order.Order;
import com.anton.gs.model.entity.order.OrderCreator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class OrderCreatorTest {
    OrderCreator orderCreator;
    @BeforeMethod
    public void setUp() {
        orderCreator = OrderCreator.getInstance();
    }

    @AfterMethod
    public void tearDown() {
        orderCreator = null;
    }

    @Test
    public void testCreteOrderPositive() {
        Order actual = orderCreator.creteOrder(2,
                "aaa@gmail.com",
                "metal",
                true,
                true);
        Order expected = orderCreator.creteOrder(2,
                "aaa@gmail.com",
                "metal",
                true,
                true);
        Assert.assertEquals(actual, expected);
    }
}