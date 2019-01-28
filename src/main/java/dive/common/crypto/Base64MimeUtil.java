package dive.common.crypto;

import java.util.Base64;
import java.util.Objects;

/**
 * Base64Mime编码工具
 *
 * @author dawn
 */
public class Base64MimeUtil {

    public static byte[] base64MimeEncode(byte[] plain) {
        Objects.requireNonNull(plain, "plain");
        return Base64.getMimeEncoder().encode(plain);
    }

    public static byte[] base64MimeDecode(byte[] base64) {
        Objects.requireNonNull(base64, "base64");
        return Base64.getMimeDecoder().decode(base64);
    }

    public static String base64MimeEncodeToString(byte[] plain) {
        return new String(base64MimeEncode(plain));
    }

    public static byte[] base64MimeDecodeBySTring(String base64) {
        return base64MimeDecode(base64.getBytes());
    }

}
