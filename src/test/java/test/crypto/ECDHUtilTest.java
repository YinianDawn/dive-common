package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.ECDHUtil.aesKeyToBase64;
import static dive.common.crypto.ECDHUtil.ecKeyToBase64;

/**
 * ECDHUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class ECDHUtilTest {
    @Test
    public void test() {
        String[] keys1 = ecKeyToBase64();
        String[] keys2 = ecKeyToBase64();
        System.out.println(keys1[0]);
        System.out.println(keys1[1]);
        System.out.println(keys2[0]);
        System.out.println(keys2[1]);
        System.out.println();
        String aes1 = aesKeyToBase64(keys1[0], keys2[1]);
        String aes2 = aesKeyToBase64(keys2[0], keys1[1]);
        System.out.println(aes1);
        System.out.println(aes2);
        Assert.assertEquals(aes1, aes2);
    }
}
