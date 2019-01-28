package dive.common.crypto;

import java.math.BigInteger;
import java.util.Objects;

import static dive.common.crypto.HexUtil.hexDecode;
import static dive.common.crypto.HexUtil.hexEncodeUpper;

/**
 * Base58Mime编码工具
 *
 * @author dawn
 */
public class Base58Util {
    /**
     * 进制
     */
    private static final BigInteger RADIX = new BigInteger("58");
    public static final char[] CHARSET_BITCOIN = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz".toCharArray();
    public static final char[] CHARSET_MONERO  = CHARSET_BITCOIN;
    public static final char[] CHARSET_RIPPLE  = "rpshnaf39wBUDNEGHJKLM4PQRST7VWXYZ2bcdeCg65jkm8oFqi1tuvAxyz".toCharArray();
    public static final char[] CHARSET_FLICKR_SHORT_URL = "123456789abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ".toCharArray();

    /**
     * base58编码
     * @param plain 明文
     * @param charset 字符集
     * @return 密文
     */
    public static String base58Encode(byte[] plain, char[] charset) {
        Objects.requireNonNull(plain, "plain");
        Objects.requireNonNull(charset, "charset");
        if (58 != charset.length) {
            throw new RuntimeException("the length of charset must be 58: " + charset.length);
        }

        BigInteger dec = new BigInteger(hexEncodeUpper(plain), 16);
        StringBuilder sb = new StringBuilder((int)(plain.length*1.37) + 1);
        while (0 < dec.compareTo(BigInteger.ZERO)) {
            BigInteger[] t = dec.divideAndRemainder(RADIX);
            dec = t[0];
            sb.append(charset[t[1].intValue()]);
        }
        for (byte b : plain) {
            if (0 != b) {
                break;
            }
            sb.append(charset[0]);
        }
        return sb.reverse().toString();
    }

    /**
     * base58解密
     * @param base58 密文
     * @param charset 字符集
     * @return 明文
     */
    public static byte[] base58Decode(String base58, char[] charset) {
        Objects.requireNonNull(base58, "base58");
        Objects.requireNonNull(charset, "charset");
        if (58 != charset.length) {
            throw new RuntimeException("the length of charset must be 58: " + charset.length);
        }

        String cs = new String(charset);
        BigInteger dec = BigInteger.ZERO;
        char[] chars = base58.toCharArray();
        for (char c : chars) {
            int num = cs.indexOf(c);
            dec = dec.multiply(RADIX).add(new BigInteger(String.valueOf(num)));
        }
        return hexDecode(dec.toString(16));
    }


    public static String base58EncodeByBitcoin(String plain) {
        return base58Encode(plain.getBytes(), CHARSET_BITCOIN);
    }


    public static String base58DecodeByBitcoin(String base58) {
        return new String(base58Decode(base58, CHARSET_BITCOIN));
    }

}
