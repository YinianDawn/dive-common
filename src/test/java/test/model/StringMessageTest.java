package test.model;

import dive.common.model.StringMessage;
import org.junit.Assert;
import org.junit.Test;

/**
 * StringMessage测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class StringMessageTest {

    @Test
    public void test() {

        StringMessage message = new StringMessage();
        Assert.assertNotNull(message);

        message.setCreated(null);
        message.setCode(1);
        message.setMessage("failed");
        message.setData("string");

        Assert.assertNull(message.getCreated());
        Assert.assertEquals(1, message.getCode());
        Assert.assertEquals("failed", message.getMessage());
        Assert.assertEquals("string", message.getData());

    }

}
