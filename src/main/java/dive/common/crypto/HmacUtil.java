package dive.common.crypto;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static dive.common.crypto.HexUtil.hexEncode;

/**
 * Hmac工具
 *
 * @author dawn
 */
public class HmacUtil {

    public static final String HMAC_MD5 = "HmacMD5";
    public static final String HMAC_SHA1 = "HmacSHA1";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA384 = "HmacSHA384";
    public static final String HMAC_SHA512 = "HmacSHA512";

    public static byte[] hmac(byte[] message, String key, String algorithm)
            throws NoSuchAlgorithmException, InvalidKeyException {
        Objects.requireNonNull(message, "message");
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), algorithm);
        Mac mac = Mac.getInstance(algorithm);
        mac.init(signingKey);
        return mac.doFinal(message);
    }

    private static String hmac(byte[] message, String key, String algorithm, boolean upper) {
        try {
            return hexEncode(hmac(message, key, algorithm), upper);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String HmacMD5(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_MD5, true);
    }

    public static String HmacSHA1(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA1, true);
    }

    public static String HmacSHA256(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA256, true);
    }

    public static String HmacSHA384(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA384, true);
    }

    public static String HmacSHA512(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA512, true);
    }

    public static String hmacMD5(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_MD5, false);
    }

    public static String hmacSHA1(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA1, false);
    }

    public static String hmacSHA256(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA256, false);
    }

    public static String hmacSHA384(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA384, false);
    }

    public static String hmacSHA512(String message, String key) {
        return hmac(message.getBytes(), key, HMAC_SHA512, false);
    }

}
