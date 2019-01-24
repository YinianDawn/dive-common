package dive.common.crypto;

import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;
import static dive.common.crypto.Util.key;

/**
 * DH秘钥交换算法
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class DHUtil {

    public static KeyPair dhKey() {
        return key("DH", 1024);
    }

    public static String[] dhKeyToBase64() {
        return key(dhKey());
    }

    public static DHPublicKey dhPublicByBase64(String dhPublicBase64) throws Exception {
        Objects.requireNonNull(dhPublicBase64, "dhPublicBase64");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64DecodeByString(dhPublicBase64));
        return (DHPublicKey) KeyFactory.getInstance("DH").generatePublic(keySpec);
    }

    public static DHPrivateKey dhPrivateByBase64(String dhPrivateBase64) throws Exception {
        Objects.requireNonNull(dhPrivateBase64, "dhPrivateBase64");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64DecodeByString(dhPrivateBase64));
        return (DHPrivateKey) KeyFactory.getInstance("DH").generatePrivate(keySpec);
    }

    public static byte[] aesKey(DHPublicKey publicKey, DHPrivateKey privateKey) {
        return Util.aesKey("DH", publicKey, privateKey);
    }

    public static String aesKeyToBase64(DHPublicKey publicKey, DHPrivateKey privateKey) {
        return base64EncodeToString(aesKey(publicKey, privateKey));
    }

    public static String aesKeyToBase64(String dhPublicBase64, String dhPrivateBase64) {
        try {
            return base64EncodeToString(aesKey(dhPublicByBase64(dhPublicBase64), dhPrivateByBase64(dhPrivateBase64)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
