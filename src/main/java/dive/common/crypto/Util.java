package dive.common.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.ArrayList;
import java.util.List;

import static dive.common.crypto.Base64Util.base64EncodeToString;

/**
 * 统一方法
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
class Util {

    /**
     * 统一加解密
     * @param bytes 待处理信息
     * @param key 秘钥
     * @param iv 初始向量
     * @param padding 填充
     * @param mode 加密还是解密
     * @return 处理结果
     * @throws GeneralSecurityException 异常
     */
    static byte[] doFinal(byte[] bytes, Key key, byte[] iv, String padding, int mode)
            throws GeneralSecurityException {
        // Cipher.getInstance(padding, "BC")
        Cipher c = Cipher.getInstance(padding);
        if (null != iv) {
            c.init(mode, key, new IvParameterSpec(iv));
        } else {
            c.init(mode, key);
        }
        return c.doFinal(bytes);
    }

    /**
     * 获取秘钥对
     * @param algorithm 算法
     * @param i 初始化值
     * @return 密钥对
     */
    static KeyPair key(String algorithm, int i) {
        try {
            KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
            generator.initialize(i);
            return generator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 密钥对转字符串对Base64编码
     * @param pair 密钥对
     * @return Base64编码后
     */
    static String[] keyToBase64(KeyPair pair) {
        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();
        return new String[]{base64EncodeToString(publicKey.getEncoded()),
                base64EncodeToString(privateKey.getEncoded())};
    }

    /**
     * 计算aes秘钥
     * @param algorithm 算法
     * @param publicKey 公钥
     * @param privateKey 私钥
     * @return 计算秘钥
     */
    static byte[] aesKey(String algorithm, PublicKey publicKey, PrivateKey privateKey) {
        try {
            KeyAgreement agreement = KeyAgreement.getInstance(algorithm);
            agreement.init(privateKey);
            agreement.doPhase(publicKey, true);
            // 需配置jvm参数才能够直接计算秘钥
            // -Djdk.crypto.KeyAgreement.legacyKDF=true
            // 这里提取前16为作为秘钥了
            byte[] secret = agreement.generateSecret();
            return new SecretKeySpec(secret, 0, 16, "AES").getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统一加解密
     * @param bytes 数据
     * @param max 长度
     * @param c 工具对象
     * @return 处理结果
     * @throws BadPaddingException 填充
     * @throws IllegalBlockSizeException 块长错误
     */
    static byte[] doFinal(byte[] bytes, int max, Cipher c)
            throws BadPaddingException, IllegalBlockSizeException {
        int size = bytes.length / max;
        int reminder = bytes.length % max;
        List<byte[]> list = new ArrayList<>(size + 1);
        int length = 0;
        for (int i = 0; i < size; i++) {
            byte[] bs = new byte[max];
            System.arraycopy(bytes, max * i, bs, 0, max);
            bs = c.doFinal(bs);
            length += bs.length;
            list.add(bs);
        }
        if (0 < reminder) {
            byte[] bs = new byte[reminder];
            System.arraycopy(bytes, max * size, bs, 0, reminder);
            bs = c.doFinal(bs);
            reminder = bs.length;
            list.add(bs);
        }

        bytes = new byte[length + reminder];
        int position = 0;
        for (int i = 0; i < size; i++) {
            byte[] bs = list.get(i);
            System.arraycopy(bs, 0, bytes, position, bs.length);
            position += bs.length;
        }
        if (0 < reminder) {
            byte[] bs = list.get(size);
            System.arraycopy(bs, 0, bytes, position, bs.length);
        }
        return bytes;
    }

    /**
     * 统一签名
     * @param message 信息
     * @param privateKey 私钥
     * @param algorithm 算法
     * @return 签名结果
     * @throws Exception 异常
     */
    static byte[] sign(byte[] message, PrivateKey privateKey, String algorithm) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(message);
        return signature.sign();
    }

    /**
     * 验证签名
     * @param message 原文
     * @param sign 签名
     * @param publicKey 公钥
     * @param algorithm 算法
     * @return 是否正确
     * @throws Exception 异常
     */
    static boolean verify(byte[] message, byte[] sign, PublicKey publicKey, String algorithm) throws Exception {
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        signature.update(message);
        return signature.verify(sign);
    }
}
