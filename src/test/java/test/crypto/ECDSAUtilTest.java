package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.ECDSAUtil.*;

/**
 * ECDSAUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class ECDSAUtilTest {

    @Test
    public void test() throws Exception {
        String[] keys = ecdsaKeyToBase64();

        System.out.println("publicKey --> \n" + keys[0]);
        System.out.println("privateKey --> \n" + keys[1]);
        String publicKey = keys[0];
        String privateKey = keys[1];

        String message = "12324dflsnfks nias年少不低啊 ほんご";

        System.out.println("--------测试签名");
        String sign = ecdsaSignToBase64(message, privateKey);
        System.out.println(sign);
        System.out.println(ecdsaVerifyByBase64(message, sign, publicKey));
        Assert.assertTrue(ecdsaVerifyByBase64(message, sign, publicKey));

    }

}