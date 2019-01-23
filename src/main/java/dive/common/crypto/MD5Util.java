package dive.common.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static dive.common.crypto.HexUtil.hexEncode;

/**
 * MD5工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class MD5Util {

    public static byte[] md5(byte[] message) {
        Objects.requireNonNull(message, "message");
        try {
            return MessageDigest.getInstance("MD5").digest(message);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String MD5(String message) {
        Objects.requireNonNull(message, "message");
        return hexEncode(md5(message.getBytes()), true);
    }

    public static String md5(String message) {
        Objects.requireNonNull(message, "message");
        return hexEncode(md5(message.getBytes()), false);
    }

}
