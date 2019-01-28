package dive.common.crypto;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;
import static dive.common.crypto.Util.*;

/**
 * RSA签名
 *
 * @author dawn
 */
public class RSAUtil {

    public static final String PADDING = "RSA/ECB/PKCS1Padding";
    public static final String ALGORITHM = "SHA256withRSA";

    public static KeyPair rsaKey() {
        return key("RSA", 2048);
    }

    public static String[] rsaKeyToBase64() {
        return keyToBase64(rsaKey());
    }

    public static RSAPublicKey rsaPublicByBase64(String rsaPublicBase64) throws Exception {
        Objects.requireNonNull(rsaPublicBase64, "rsaPublicBase64");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(base64DecodeByString(rsaPublicBase64));
        return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    public static RSAPrivateKey rsaPrivateByBase64(String rsaPrivateBase64) throws Exception {
        Objects.requireNonNull(rsaPrivateBase64, "rsaPrivateBase64");
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64DecodeByString(rsaPrivateBase64));
        return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(keySpec);
    }

    public static byte[] rsaEncrypt(byte[] plain, RSAPublicKey rsaPublic, String padding) throws Exception {
        Objects.requireNonNull(plain, "plain");
        Cipher c = Cipher.getInstance(padding);
        c.init(Cipher.ENCRYPT_MODE, rsaPublic);
        // 加密数据长度 <= 模长-11
        int max = rsaPublic.getModulus().bitLength() / 8 - 11;
        return doFinal(plain, max, c);
    }

    public static byte[] rsaDecrypt(byte[] cipher, RSAPrivateKey rsaPrivate, String padding) throws Exception {
        Objects.requireNonNull(cipher, "cipher");
        Cipher c = Cipher.getInstance(padding);
        c.init(Cipher.DECRYPT_MODE, rsaPrivate);
        //模长
        int max = rsaPrivate.getModulus().bitLength() / 8;
        return doFinal(cipher, max, c);
    }

    public static byte[] rsaSign(byte[] message, RSAPrivateKey rsaPrivate, String algorithm) throws Exception {
        Objects.requireNonNull(message, "message");
        return sign(message, rsaPrivate, algorithm);
    }

    public static boolean rsaVerify(byte[] message, byte[] sign, RSAPublicKey rsaPublic, String algorithm) throws Exception {
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(sign, "sign");
        return verify(message, sign, rsaPublic, algorithm);
    }

    public static String rsaEncryptToBase64(String plain, String rsaPublicBase64) throws Exception {
        Objects.requireNonNull(plain, "plain");
        return base64EncodeToString(rsaEncrypt(plain.getBytes(), rsaPublicByBase64(rsaPublicBase64), PADDING));
    }

    public static String rsaDecryptToBase64(String cipherBase64, String rsaPrivateBase64) throws Exception {
        Objects.requireNonNull(cipherBase64, "cipherBase64");
        return new String(rsaDecrypt(base64DecodeByString(cipherBase64), rsaPrivateByBase64(rsaPrivateBase64), PADDING));
    }

    public static String rsaSignToBase64(String message, String rsaPrivateBase64) throws Exception {
        Objects.requireNonNull(message, "message");
        return base64EncodeToString(rsaSign(message.getBytes(), rsaPrivateByBase64(rsaPrivateBase64), ALGORITHM));
    }

    public static boolean rsaVerifyByBase64(String message, String signBase64, String rsaPublicBase64) throws Exception {
        Objects.requireNonNull(message, "message");
        Objects.requireNonNull(signBase64, "signBase64");
        return rsaVerify(message.getBytes(), base64DecodeByString(signBase64), rsaPublicByBase64(rsaPublicBase64), ALGORITHM);
    }

}
