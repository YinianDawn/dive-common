package test.crypto;

import dive.common.crypto.Base32Util;
import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.Base32Util.*;

/**
 * HexUtil测试
 *
 * @author dawn
 */
public class Base32UtilTest {

    @Test
    public void test() {
        Assert.assertEquals("EU======", base32Encode(new byte[]{'%'}, Base32Util.RFC4648));
        Assert.assertArrayEquals(new byte[]{'%'}, base32Decode("EU======", Base32Util.RFC4648));
        Assert.assertEquals("MFRGGZDFMZTWQ2I=", base32EncodeByRFC4648("abcdefghi".getBytes()));
        Assert.assertEquals("abcdefghi", new String(base32DecodeByRFC4648("MFRGGZDFMZTWQ2I=")));
    }

}
