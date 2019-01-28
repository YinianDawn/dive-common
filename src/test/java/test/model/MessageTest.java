package test.model;

import dive.common.model.Message;
import org.junit.Assert;
import org.junit.Test;

/**
 * Message测试
 *
 * @author dawn
 */
public class MessageTest {

    @Test
    public void test() {

        Assert.assertEquals(0, Message.CODE_SUCCESS);
        Assert.assertEquals(1, Message.CODE_FAILED);

        Message<Integer> message = new Message<>();
        Assert.assertNotNull(message);

        message = new Message<>(5, "fail");
        Assert.assertNotNull(message);

        message = new Message<>(5, "fail", 6);
        Assert.assertNotNull(message);

        message = new Message<>(6);
        Assert.assertNotNull(message);

        message = Message.success();
        Assert.assertNotNull(message);

        message = Message.success("success");
        Assert.assertNotNull(message);

        message = Message.fail();
        Assert.assertNotNull(message);

        message = Message.fail("failed");
        Assert.assertNotNull(message);

        Assert.assertNotNull(message.getCreated());
        Assert.assertEquals(1, message.getCode());
        Assert.assertEquals("failed", message.getMessage());
        Assert.assertNull(message.getData());
        message.setCode(0);
        message.setMessage("success");
        message.setData(7);

        Assert.assertFalse(message.code(1).message("failed").data(7).fine());

    }

}
