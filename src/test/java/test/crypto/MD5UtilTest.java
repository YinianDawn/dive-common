package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.MD5Util.MD5;
import static dive.common.crypto.MD5Util.md5;

/**
 * MD5Util测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class MD5UtilTest {

    @Test
    public void test() {
        String message = "123";
        Assert.assertEquals("202CB962AC59075B964B07152D234B70", MD5(message));
        Assert.assertEquals("202cb962ac59075b964b07152d234b70", md5(message));
    }

}
