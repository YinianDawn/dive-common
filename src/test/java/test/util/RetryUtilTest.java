package test.util;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.util.TryUtil.retry;

/**
 * RetryUtil测试
 *
 * @author dawn
 */
public class RetryUtilTest {
    private int times = 0;
    private Object supplierObject() { return "123"; }
    private Object supplierNull() { times++; return null; }

    private Object functionObject(Object obj) { return "123"; }
    private Object functionNull(Object obj) { return null; }

    private Object biFunctionObject(Object obj1, Object obj2) { return "123"; }
    private Object biFunctionNull(Object obj1, Object obj2) { return null; }

    @Test
    public void test() {
        RetryUtilTest test = new RetryUtilTest();

        Assert.assertNotNull(retry(test::supplierObject));
        Assert.assertNull(retry(test::supplierNull));
        Assert.assertNull(retry(test::supplierNull, 100));

        Assert.assertNotNull(retry(test::functionObject, "123"));
        Assert.assertNull(retry(test::functionNull, "123"));
        Assert.assertNull(retry(test::functionNull, "123", 100));

        Assert.assertNotNull(retry(test::biFunctionObject, "123", "456"));
        Assert.assertNull(retry(test::biFunctionNull, "123", "456"));
        Assert.assertNull(retry(test::biFunctionNull, "123", "456", 100));
    }

}
