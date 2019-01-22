package test.crypto;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.crypto.HexUtil.*;

/**
 * HexUtil测试
 *
 * @author dawn
 * @date 2019/1/19 22:57
 */
public class HexUtilTest {
    @Test
    public void test() {
        byte[] bytes = new byte[]{123, 12, 124, 123};
        Assert.assertEquals("7B0C7C7B", hexEncodeUpper(bytes));
        Assert.assertEquals(byte2hex(bytes), hexEncodeUpper(bytes));
        Assert.assertEquals("7b0c7c7b", hexEncodeLower(bytes));
        Assert.assertArrayEquals(bytes, hexDecode("7B0C7C7B"));
        Assert.assertArrayEquals(hex2byte("7B0C7C7B"), hexDecode("7B0C7C7B"));
        Assert.assertArrayEquals(bytes, hexDecode("7b0c7c7b"));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) hexEncodeUpper(bytes);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) byte2hex(bytes);
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) hexDecode("7B0C7C7B");
        end = System.currentTimeMillis();
        System.out.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) hex2byte("7B0C7C7B");
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static byte[] hex2byte(String hex) {
        if (null == hex) return null;
        int l = hex.length();
        if (1 == l % 2) return null;
        byte[] b = new byte[l / 2];
        for (int i = 0; i != l / 2; i++) {
            b[i] = (byte) Integer.parseInt(hex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    private static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        String temp;
        for (byte aByte : bytes) {
            temp = (Integer.toHexString(aByte & 0XFF));
            if (temp.length() == 1) {
                sb.append("0").append(temp);
            } else {
                sb.append(temp);
            }
        }
        return sb.toString().toUpperCase();
    }
}
