package test.util;

import dive.common.util.UrlUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * UrlUtil测试
 *
 * @author dawn
 */
public class UrlUtilTest {

    @Test
    public void test() {
        Assert.assertEquals("https%3A%2F%2Fwww.google.com", UrlUtil.urlEncode("https://www.google.com"));
    }

}
