package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.AESUtil.*;
import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;

/**
 * AESUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class AESUtilTest {

    @Test
    public void test() {
        String key = aesKeyToBase64();
        System.out.println("secret:");
        System.out.println(key);

        key = "1234567812345678";
        key = base64EncodeToString(key.getBytes());

        String plain = "AES";
        System.out.println("plain:");
        System.out.println(plain);

        String cipher = aesEncryptToBase64(plain, key);
        System.out.println("cipher:");
        System.out.println(cipher);
        Assert.assertEquals("Lak2LtylufEUjD76s5P1Dg==", cipher);

        String plain2 = aesDecryptByBase64(cipher, key);
        System.out.println("plain2:");
        System.out.println(plain2);
        Assert.assertEquals(plain, plain2);

    }

    @Test
    public void test2() throws Exception {
        String key = "1234567812345678";
        String secret = base64EncodeToString(key.getBytes());

        String plain = "AES";
        System.out.println("plain:");
        System.out.println(plain);

        String cipher = base64EncodeToString(aesEncrypt(plain.getBytes(), base64DecodeByString(secret), null, "AES/ECB/PKCS5Padding"));
        System.out.println("cipher:");
        System.out.println(cipher);
        Assert.assertEquals("bigEf8eBGPkkd/6NUhZ1/g==", cipher);

        String plain2 = new String(aesDecrypt(base64DecodeByString(cipher), base64DecodeByString(secret), null, "AES/ECB/PKCS5Padding"));
        System.out.println("plain2:");
        System.out.println(plain2);
        Assert.assertEquals(plain, plain2);

    }

}
