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
import static dive.common.crypto.Util.key;
import static dive.common.crypto.Util.keyToBase64;

/**
 * ECDH秘钥交换算法
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class ECDHUtil {

    public static KeyPair ecKey() {
        return key("EC", 256);
    }

    public static String[] ecKeyToBase64() {
        return keyToBase64(ecKey());
    }

    public static ECPublicKey ecPublicByBase64(String ecPublicBase64) throws Exception {
        Objects.requireNonNull(ecPublicBase64, "ecPublicBase64");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64DecodeByString(ecPublicBase64));
        return (ECPublicKey) KeyFactory.getInstance("EC").generatePublic(keySpec);
    }

    public static ECPrivateKey ecPrivateByBase64(String ecPrivateBase64) throws Exception {
        Objects.requireNonNull(ecPrivateBase64, "ecPrivateBase64");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64DecodeByString(ecPrivateBase64));
        return (ECPrivateKey) KeyFactory.getInstance("EC").generatePrivate(keySpec);
    }

    public static byte[] aesKey(ECPublicKey publicKey, ECPrivateKey privateKey) {
        return Util.aesKey("ECDH", publicKey, privateKey);
    }

    public static String aesKeyToBase64(ECPublicKey publicKey, ECPrivateKey privateKey) {
        return base64EncodeToString(aesKey(publicKey, privateKey));
    }

    public static String aesKeyToBase64(String ecPublicBase64, String ecPrivateBase64) {
        try {
            return base64EncodeToString(aesKey(ecPublicByBase64(ecPublicBase64), ecPrivateByBase64(ecPrivateBase64)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
