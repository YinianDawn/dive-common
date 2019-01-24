package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.RSAUtil.*;

/**
 * RSAUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class RSAUtilTest {

    @Test
    public void test() throws Exception {
        String[] keys = rsaKeyToBase64();

        System.out.println("publicKey --> \n" + keys[0]);
        System.out.println("privateKey --> \n" + keys[1]);
        String publicKey = keys[0];
        String privateKey = keys[1];

        System.out.println("--------测试加密");
        String plain = "test" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "你傻的腐败就是に" +
                "ほんご";
        System.out.println(plain);

        String cipher = rsaEncryptToBase64(plain, publicKey);
        System.out.println(cipher);
        String plain2 = rsaDecryptToBase64(cipher, privateKey);
        System.out.println(plain2);
        Assert.assertEquals(plain, plain2);

        System.out.println("--------测试签名");
        String sign = rsaSignToBase64(plain, privateKey);
        System.out.println(sign);
        System.out.println(rsaVerifyByBase64(plain, sign, publicKey));
        Assert.assertTrue(rsaVerifyByBase64(plain, sign, publicKey));
    }

}
