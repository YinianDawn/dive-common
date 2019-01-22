package dive.common.crypto;

import java.util.Objects;

/**
 * Base32编码工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class Base32Util {

    /**
     * RFC4648的Base32编码
     */
    public static char[] RFC4648 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567".toCharArray();

    /**
     * 将byte转换成字符串，长度不足前面补"0"
     * @param b byte
     * @param length 要求长度
     * @return 二进制
     */
    private static StringBuilder binary(byte b, int length) {
        StringBuilder sb = new StringBuilder(length);
        sb.append(Integer.toBinaryString(b & 0XFF));
        while (sb.length() < length) {
            sb.insert(0, "0");
        }
        return sb;
    }

    /**
     * Base32编码
     * @param plain 原始信息
     * @param charset 编码表
     * @return Base32编码结果
     */
    public static String base32Encode(byte[] plain, char[] charset) {
        Objects.requireNonNull(plain, "plain");

        StringBuilder sb = new StringBuilder(plain.length * 8 + 4);
        for (byte b : plain) {
            sb.append(binary(b, 8));
        }
        while (0 != sb.length() % 5) {
            sb.append("0");
        }

        int length = sb.length() / 5;
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            result.append(charset[Integer.parseInt(sb.substring(i * 5, i * 5 + 5), 2)]);
        }
        int reminder = sb.length() % 40;
        if (0 != reminder) {
            for (int i = 0; i < 8 - reminder / 5; i++) {
                result.append("=");
            }
        }
        return result.toString();
    }

    /**
     * Base32解码
     * @param cipher 加密
     * @param charset 编码表
     * @return 原始信息
     */
    public static byte[] base32Decode(String cipher, char[] charset) {
        Objects.requireNonNull(cipher, "cipher");

        while (cipher.endsWith("=")) {
            cipher = cipher.substring(0, cipher.length() - 1);
        }

        String cs = new String(charset);
        StringBuilder sb = new StringBuilder();
        for (byte b : cipher.getBytes()) {
            sb.append(binary((byte) cs.indexOf(b), 5));
        }
        int length = sb.length() / 8;
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = (byte) Integer.parseInt(sb.substring(i * 8, i * 8 + 8), 2);
        }
        return result;
    }

    public static String base32EncodeByRFC4648(byte[] plain) {
        return base32Encode(plain, RFC4648);
    }

    public static byte[] base32DecodeByRFC4648(String cipher) {
        return base32Decode(cipher, RFC4648);
    }

}
