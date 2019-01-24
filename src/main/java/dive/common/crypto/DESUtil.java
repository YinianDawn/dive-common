package dive.common.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static dive.common.crypto.Base64Util.base64DecodeByString;
import static dive.common.crypto.Base64Util.base64EncodeToString;

/**
 * DES加密工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class DESUtil {

    public static final byte[] IV = "12345678".getBytes();
    public static final String PADDING = "DES/CBC/PKCS5Padding";

    /**
     * des 秘钥
     * @return des秘钥
     */
    public static byte[] desKey() {
        try {
            // Boucy Castle 有 PKCS7Padding 填充模式 添加 "BC" 参数， init(64)
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            generator.init(56);
            return generator.generateKey().getEncoded();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    public static String desKeyToBase64() {
        return base64EncodeToString(desKey());
    }

    private static byte[] crypt(byte[] bytes, byte[] key, byte[] iv, String padding, int mode)
            throws GeneralSecurityException {
        return Util.doFinal(bytes,
                SecretKeyFactory.getInstance("DES")
                        .generateSecret(new DESKeySpec(key)),
                iv, padding, mode);
    }

    public static byte[] desEncrypt(byte[] plain, byte[] key, byte[] iv, String padding)
            throws GeneralSecurityException {
        Objects.requireNonNull(plain, "plain");
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(padding, "padding");
        return crypt(plain, key, iv, padding, Cipher.ENCRYPT_MODE);
    }

    public static byte[] desDecrypt(byte[] cipher, byte[] key, byte[] iv, String padding)
            throws GeneralSecurityException {
        Objects.requireNonNull(cipher, "cipher");
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(padding, "padding");
        return crypt(cipher, key, iv, padding, Cipher.DECRYPT_MODE);
    }

    public static String desEncryptToBase64(String plain, String keyBase64) {
        Objects.requireNonNull(plain, "plain");
        Objects.requireNonNull(keyBase64, "keyBase64");
        try {
            byte[] bytes = desEncrypt(plain.getBytes(), base64DecodeByString(keyBase64), IV, PADDING);
            return base64EncodeToString(bytes);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String desDecryptByBase64(String cipherBase64, String keyBase64) {
        Objects.requireNonNull(cipherBase64, "cipherBase64");
        Objects.requireNonNull(keyBase64, "keyBase64");
        try {
            byte[] bytes = desDecrypt(base64DecodeByString(cipherBase64), base64DecodeByString(keyBase64), IV, PADDING);
            return new String(bytes);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
