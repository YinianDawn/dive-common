package test.crypto;

import dive.common.crypto.Base32Util;
import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.GAUtil.*;

/**
 * GAUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class GAUtilTest {

    @Test
    public void test() {
        System.out.println(gaKeyToBase32ByRFC4648(16));
        System.out.println(gaKeyToBase32ByRFC4648(32));
        System.out.println(code(gaKeyToBase32ByRFC4648(32)));
        String key = "JRSIR6O6VKTZBYRR";
        System.out.println(System.currentTimeMillis());
        System.out.println(code(key));
        Assert.assertEquals("272241", code(key, 1546250674280L));
        Assert.assertTrue(verify("272241", Base32Util.base32DecodeByRFC4648(key), 1546250674280L, 1));
    }

}
