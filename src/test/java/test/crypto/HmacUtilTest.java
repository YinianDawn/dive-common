package test.crypto;

import dive.common.crypto.HmacUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * HmacUtil测试
 *
 * @author dawn
 */
public class HmacUtilTest {

    @Test
    public void test() {
        String message = "abc";
        Assert.assertEquals("f71cda1c893766a115234db7fdd59f63", HmacUtil.hmacMD5(message, message));
        Assert.assertEquals("5b333a389b4e9a2358ac5392bf2a64dc68e3c943", HmacUtil.hmacSHA1(message, message));
        Assert.assertEquals("2f02e24ae2e1fe880399f27600afa88364e6062bf9bbe114b32fa8f23d03608a", HmacUtil.hmacSHA256(message, message));
        Assert.assertEquals("ef38b92496d409ab82a4ebc44724b39c0be52da9805db4605958935dd74f8a16258f403b5572cce139825caec1657893", HmacUtil.hmacSHA384(message, message));
        Assert.assertEquals("1a97e05c35e6727690dfdf2e8079b34fefabf15236abc9170dccdcf5623e4c5ce72a446842bd7607186c9e3f21c0a0edf6ab6c5ec8304a1f969c20c1455e9b7c", HmacUtil.hmacSHA512(message, message));

    }

}
