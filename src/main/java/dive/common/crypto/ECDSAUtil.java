package dive.common.crypto;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;
import static dive.common.crypto.Util.*;

/**
 * ECDSA签名算法
 *
 * @author dawn
 */
public class ECDSAUtil {

    public static final String ALGORITHM = "SHA256withECDSA";

    public static KeyPair ecdsaKey() {
        return key("EC", 256);
    }

    public static String[] ecdsaKeyToBase64() {
        return keyToBase64(ecdsaKey());
    }

    public static ECPublicKey ecdsaPublicByBase64(String ecdsaPublicBase64) throws Exception {
        Objects.requireNonNull(ecdsaPublicBase64, "ecdsaPublicBase64");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64DecodeByString(ecdsaPublicBase64));
        return (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(keySpec);
    }

    public static ECPrivateKey ecdsaPrivateByBase64(String ecdsaPrivateBase64) throws Exception {
        Objects.requireNonNull(ecdsaPrivateBase64, "ecdsaPrivateBase64");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64DecodeByString(ecdsaPrivateBase64));
        return (ECPrivateKey) KeyFactory.getInstance("EC").generatePrivate(keySpec);
    }

    public static byte[] ecdsaSign(byte[] message, ECPrivateKey ecdsaPrivate, String algorithm) throws Exception {
        Objects.requireNonNull(message, "message");
        return sign(message, ecdsaPrivate, algorithm);
    }

    public static boolean ecdsaVerify(byte[] message, byte[] sign, ECPublicKey ecdsaPublic, String algorithm) throws Exception {
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(sign, "sign");
        return verify(message, sign, ecdsaPublic, algorithm);
    }

    public static String ecdsaSignToBase64(String message, String ecdsaPrivateBase64) throws Exception {
        Objects.requireNonNull(message, "message");
        return base64EncodeToString(ecdsaSign(message.getBytes(), ecdsaPrivateByBase64(ecdsaPrivateBase64), ALGORITHM));
    }

    public static boolean ecdsaVerifyByBase64(String message, String signBase64, String ecdsaPublicBase64) throws Exception {
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(signBase64, "signBase64");
        return ecdsaVerify(message.getBytes(), base64DecodeByString(signBase64), ecdsaPublicByBase64(ecdsaPublicBase64), ALGORITHM);
    }

}
