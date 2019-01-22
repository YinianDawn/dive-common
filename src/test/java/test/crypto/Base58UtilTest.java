package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.Base58Util.base58DecodeByBitcoin;
import static dive.common.crypto.Base58Util.base58EncodeByBitcoin;

/**
 * Base58Util测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class Base58UtilTest {
    @Test
    public void test() {
//        String plain = token(128) + "你好￥%**";
        String plain = "Java加密与解密的艺术";
        String cipher;
        String plain2;
        System.out.println("plain:\n" + plain);

        System.out.println("===============");
        cipher = base58EncodeByBitcoin(plain);
        System.out.println("cipher:\n" + cipher);
//        Assert.assertEquals("mNYZfDwvXwbhax7T5S3AkfCncwrSF6yHtD8c5c", cipher);
        plain2 = base58DecodeByBitcoin(cipher);
        System.out.println("plain2:\n" + plain2);
        Assert.assertEquals(plain, plain2);

    }
}
