package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.Base64Util.*;

/**
 * Base64Util测试
 *
 * @author dawn
 */
public class Base64UtilTest {
    @Test
    public void test() {
//        String plain = token(128) + "你好￥%**";
        String plain = "Java加密与解密的艺术";
        String cipher;
        String plain2;
        System.out.println("plain:\n" + plain);

        System.out.println("===============");
        cipher = base64EncodeToString(plain.getBytes());
        System.out.println("cipher:\n" + cipher);
//        Assert.assertEquals("SmF2YeWKoOWvhuS4juino+WvhueahOiJuuacrw==", cipher);
        plain2 = new String(base64DecodeByString(cipher));
        System.out.println("plain2:\n" + plain2);
        Assert.assertEquals(plain, plain2);

    }
}
