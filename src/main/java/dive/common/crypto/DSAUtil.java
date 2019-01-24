package dive.common.crypto;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;
import static dive.common.crypto.Util.*;

/**
 * DSA签名
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class DSAUtil {

    public static final String ALGORITHM = "SHA256withDSA";

    public static KeyPair dsaKey() {
        return key("DSA", 2048);
    }

    public static String[] dsaKeyToBase64() {
        return keyToBase64(dsaKey());
    }

    public static DSAPublicKey dsaPublicByBase64(String dsaPublicBase64) throws Exception {
        Objects.requireNonNull(dsaPublicBase64, "dsaPublicBase64");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64DecodeByString(dsaPublicBase64));
        return (DSAPublicKey) KeyFactory.getInstance("DSA").generatePublic(keySpec);
    }

    public static DSAPrivateKey dsaPrivateByBase64(String dsaPrivateBase64) throws Exception {
        Objects.requireNonNull(dsaPrivateBase64, "dsaPrivateBase64");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64DecodeByString(dsaPrivateBase64));
        return (DSAPrivateKey) KeyFactory.getInstance("DSA").generatePrivate(keySpec);
    }

    public static byte[] dsaSign(byte[] message, DSAPrivateKey dsaPrivate, String algorithm) throws Exception {
        Objects.requireNonNull(message, "message");
        return sign(message, dsaPrivate, algorithm);
    }

    public static boolean dsaVerify(byte[] message, byte[] sign, DSAPublicKey dsaPublic, String algorithm) throws Exception {
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(sign, "sign");
        return verify(message, sign, dsaPublic, algorithm);
    }

    public static String dsaSignToBase64(String message, String dsaPrivateBase64) throws Exception {
        Objects.requireNonNull(message, "message");
        return base64EncodeToString(dsaSign(message.getBytes(), dsaPrivateByBase64(dsaPrivateBase64), ALGORITHM));
    }

    public static boolean dsaVerifyByBase64(String message, String signBase64, String dsaPublicBase64) throws Exception {
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(signBase64, "signBase64");
        return dsaVerify(message.getBytes(), base64DecodeByString(signBase64), dsaPublicByBase64(dsaPublicBase64), ALGORITHM);
    }

}
