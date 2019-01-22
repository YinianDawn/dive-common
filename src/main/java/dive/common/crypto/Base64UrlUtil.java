package dive.common.crypto;

import java.util.Base64;
import java.util.Objects;

/**
 * Base64Url编码工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class Base64UrlUtil {

    public static byte[] base64UrlEncode(byte[] plain) {
        Objects.requireNonNull(plain, "plain");
        return Base64.getUrlEncoder().encode(plain);
    }

    public static byte[] base64UrlDecode(byte[] base64) {
        Objects.requireNonNull(base64, "base64");
        return Base64.getUrlDecoder().decode(base64);
    }

    public static String base64UrlEncodeToString(byte[] plain) {
        return new String(base64UrlEncode(plain));
    }

    public static byte[] base64UrlDecodeByString(String base64) {
        return base64UrlDecode(base64.getBytes());
    }
}
