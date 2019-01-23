package dive.common.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static dive.common.crypto.HexUtil.hexEncode;

/**
 * SHA工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class SHAUtil {

    private static final String ALGORITHM_SHA = "SHA";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_SHA384 = "SHA-384";
    private static final String ALGORITHM_SHA512 = "SHA-512";

    public static byte[] sha(byte[] message, String algorithm) throws NoSuchAlgorithmException {
        Objects.requireNonNull(message, "message");
        return MessageDigest.getInstance(algorithm).digest(message);
    }

    private static String sha(byte[] message, String algorithm, boolean upper) {
        try {
            return hexEncode(sha(message, algorithm), upper);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String SHA(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA, true);
    }

    public static String SHA256(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA256, true);
    }

    public static String SHA384(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA384, true);
    }

    public static String SHA512(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA512, true);
    }

    public static String sha(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA, false);
    }

    public static String sha256(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA256, false);
    }

    public static String sha384(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA384, false);
    }

    public static String sha512(String message) {
        return sha(message.getBytes(), ALGORITHM_SHA512, false);
    }

}
