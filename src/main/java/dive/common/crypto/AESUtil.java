package dive.common.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;
import static dive.common.crypto.Util.doFinal;

/**
 * AES工具
 *
 * @author dawn
 */
public class AESUtil {

    public static final byte[] IV = "1234567812345678".getBytes();
    public static final String PADDING = "AES/CBC/PKCS5Padding";

    public static byte[] aesKey(int i) {
        if (i == 128 || i == 192 || i == 256) {
            try {
                // Boucy Castle 有 PKCS7Padding 填充模式 "BC" init()
                KeyGenerator generator = KeyGenerator.getInstance("AES");
                generator.init(i);
                return generator.generateKey().getEncoded();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String aesKeyToBase64() {
        return base64EncodeToString(aesKey(256));
    }

    private static byte[] crypt(byte[] bytes, byte[] key, byte[] iv, String padding, int mode)
            throws GeneralSecurityException {
        return doFinal(bytes,
                new SecretKeySpec(key, "AES"),
                iv, padding, mode);
    }

    public static byte[] aesEncrypt(byte[] plain, byte[] key, byte[] iv, String padding)
            throws GeneralSecurityException {
        return crypt(plain, key, iv, padding, Cipher.ENCRYPT_MODE);
    }

    public static byte[] aesDecrypt(byte[] cipher, byte[] key, byte[] iv, String padding)
            throws GeneralSecurityException {
        return crypt(cipher, key, iv, padding, Cipher.DECRYPT_MODE);
    }

    public static String aesEncryptToBase64(String plain, String keyBase64) {
        Objects.requireNonNull(plain, "plain");
        Objects.requireNonNull(keyBase64, "keyBase64");
        try {
            byte[] bytes = aesEncrypt(plain.getBytes(), base64DecodeByString(keyBase64), IV, PADDING);
            return base64EncodeToString(bytes);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesDecryptByBase64(String cipherBase64, String keyBase64) {
        Objects.requireNonNull(cipherBase64, "cipherBase64");
        Objects.requireNonNull(keyBase64, "keyBase64");
        try {
            byte[] bytes = aesDecrypt(base64DecodeByString(cipherBase64), base64DecodeByString(keyBase64), IV, PADDING);
            return new String(bytes);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
