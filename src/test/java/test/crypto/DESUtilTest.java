package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;
import static dive.common.crypto.DESUtil.*;

/**
 * DESUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class DESUtilTest {

    @Test
    public void test() {
        String key = desKeyToBase64();
        System.out.println("secret:");
        System.out.println(key);

        key = "12345678";
        key = base64EncodeToString(key.getBytes());

        String plain = "DES";
        System.out.println("plain:");
        System.out.println(plain);

        String cipher = desEncrypt(plain, key);
        System.out.println("cipher:");
        System.out.println(cipher);
        Assert.assertEquals("amCujZxo6Ig=", cipher);

        String plain2 = desDecrypt(cipher, key);
        System.out.println("plain2:");
        System.out.println(plain2);
        Assert.assertEquals(plain, plain2);
    }

    @Test
    public void test2() throws Exception {
        String key = "12345678";
        String secret = base64EncodeToString(key.getBytes());

        String plain = "DES";
        System.out.println("plain:");
        System.out.println(plain);

        String cipher = base64EncodeToString(desEncrypt(plain.getBytes(), base64DecodeByString(secret), null, "DES/ECB/PKCS5Padding"));
        System.out.println("cipher:");
        System.out.println(cipher);
        Assert.assertEquals("GI45NYT5Bbg=", cipher);

        String plain2 = new String(desDecrypt(base64DecodeByString(cipher), base64DecodeByString(secret), null, "DES/ECB/PKCS5Padding"));
        System.out.println("plain2:");
        System.out.println(plain2);
        Assert.assertEquals(plain, plain2);
    }

}
