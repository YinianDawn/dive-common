package dive.common.crypto;

import java.util.Base64;
import java.util.Objects;

/**
 * Base64编码工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class Base64Util {

    /**
     * Base64编码
     * @param plain 明文
     * @return 密文
     */
    public static byte[] base64Encode(byte[] plain) {
        Objects.requireNonNull(plain, "plain");
        return Base64.getEncoder().encode(plain);
    }

    /**
     * Base64解码
     * @param base64 密文
     * @return 明文
     */
    public static byte[] base64Decode(byte[] base64) {
        Objects.requireNonNull(base64, "base64");
        return Base64.getDecoder().decode(base64);
    }


    public static String base64EncodeToString(byte[] plain) {
        return new String(base64Encode(plain));
    }

    public static byte[] base64DecodeByString(String base64) {
        return base64Decode(base64.getBytes());
    }

}
