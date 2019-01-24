package dive.common.crypto;

import dive.common.util.UrlUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 谷歌验证器验证
 */
public class GAUtil {

    /**
     * 生成key
     * @param size 秘钥长度
     * @return key
     */
    public static byte[] gaKey(int size) {
        try {
            return SecureRandom.getInstance("SHA1PRNG").generateSeed(size);
        } catch (NoSuchAlgorithmException ignored) {

        }
        return null;
    }

    /**
     * Base32编码的key
     * @param size 支持获取Base32编码结果为16位或者32位的秘钥，实际秘钥10或20字节
     * @return key
     */
    public static String gaKeyToBase32ByRFC4648(int size) {
        if (16 == size) {
            size = 10;
        } else if (32 == size) {
            size = 20;
        }
        return Base32Util.base32EncodeByRFC4648(gaKey(size));
    }

    /**
     * 根据秘钥和时间片计算code
     * @param key 秘钥
     * @param count 时间片
     * @return code
     */
    private static long calculate(byte[] key, long count) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = count;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        truncatedHash &= 0x7FFFFFFF;
        return truncatedHash % 1000000;
    }

    /**
     * 通过秘钥和时间计算
     * @param key key
     * @param time 时间戳
     * @return code
     */
    public static String code(byte[] key, long time) {
        try {
            long hash = calculate(key, time / 1000L / 30L);
            StringBuilder code = new StringBuilder(6);
            code.append(hash);
            while (code.length() < 6) {
                code.insert(0, "0");
            }
            return code.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过秘钥和时间计算
     * @param key Base32编码的key
     * @param time 时间戳
     * @return code
     */
    public static String code(String key, long time) {
        return code(Base32Util.base32DecodeByRFC4648(key), time);
    }

    /**
     * 通过秘钥和时间计算
     * @param key Base32编码的key
     * @return code
     */
    public static String code(String key) {
        return code(Base32Util.base32DecodeByRFC4648(key), System.currentTimeMillis());
    }

    /**
     * 验证是否正确
     * @param code 用户输入的code
     * @param key 秘钥
     * @param time 时间戳
     * @param offset 允许偏移
     * @return 是否正确
     */
    public static boolean verify(String code, byte[] key, long time, int offset) {
        long number = Long.parseLong(code);
        long count = time / 1000L / 30L;
        try {
            long hash = calculate(key, count);
            if (number == hash) {
                return true;
            }
            for (int i = 1; i <= offset; i++) {
                hash = calculate(key, count + i);
                if (number == hash) {
                    return true;
                }
                hash = calculate(key, count - i);
                if (number == hash) {
                    return true;
                }
            }
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 验证是否正确
     * @param code 用户输入的code
     * @param key 秘钥
     * @param offset 允许偏移
     * @return 是否正确
     */
    public static boolean verify(String code, byte[] key, int offset) {
        return verify(code, key, System.currentTimeMillis(), offset);
    }

    /**
     * 验证是否正确
     * @param code 用户输入的code
     * @param key Base32编码的key
     * @param offset 允许偏移
     * @return 是否正确
     */
    public static boolean verify(String code, String key, int offset) {
        return verify(code, Base32Util.base32DecodeByRFC4648(key), offset);
    }


    /**
     * QRUrl扫描内容
     * @param issuer 发布者
     * @param id 用户识别
     * @param key 秘钥
     * @return url
     */
    public static String qrUrl(String issuer, String id, String key) {
        issuer = UrlUtil.urlEncode(issuer);
        id = UrlUtil.urlEncode(id);
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                issuer, id, key, issuer);
    }
}
