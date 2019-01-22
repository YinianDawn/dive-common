package dive.common.math;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class RandomUtil {

    /**
     * 随机浮点数
     *
     * @return [0, 1)
     */
    public static double nextDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    /**
     * 随机布尔值
     *
     * @return true or false
     */
    public static boolean nextBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }


    /**
     * 0            bound = 0
     * [0, bound)   bound > 0
     * (bound, 0]   bound < 0
     */
    public static int random(int bound) {
        if (bound == 0) {
            return 0;
        }
        if (bound < 0) {
            return - ThreadLocalRandom.current().nextInt(- bound);
        }
        return ThreadLocalRandom.current().nextInt(bound);
    }

    /**
     * origin            bound = origin
     * [origin, bound)   bound > origin
     * (bound, origin]   bound < origin
     */
    public static int random(int origin, int bound) {
        if (origin == bound) {
            return origin;
        }
        if (bound < origin) {
            return origin - ThreadLocalRandom.current().nextInt(origin - bound);
        }
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }

    /**
     * origin                   range = 0
     * [origin, origin + range) range > origin
     * (origin - range, origin] range < origin
     */
    public static int randomRange(int origin, int range) {
        if (0 == range) {
            return origin;
        }
        if (range < 0) {
            return origin - ThreadLocalRandom.current().nextInt(- range);
        }
        return origin + ThreadLocalRandom.current().nextInt(range);
    }



    /**
     * 0            bound = 0
     * [0, bound)   bound > 0
     * (bound, 0]   bound < 0
     */
    public static long random(long bound) {
        if (bound == 0) {
            return 0;
        }
        if (bound < 0) {
            return - ThreadLocalRandom.current().nextLong(- bound);
        }
        return ThreadLocalRandom.current().nextLong(bound);
    }

    /**
     * origin            bound = origin
     * [origin, bound)   bound > origin
     * (bound, origin]   bound < origin
     */
    public static long random(long origin, long bound) {
        if (origin == bound) {
            return origin;
        }
        if (bound < origin) {
            return origin - ThreadLocalRandom.current().nextLong(origin - bound);
        }
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }

    /**
     * origin                   range = 0
     * [origin, origin + range) range > origin
     * (origin - range, origin] range < origin
     */
    public static long randomRange(long origin, long range) {
        if (range == 0) {
            return origin;
        }
        if (range < 0) {
            return origin - ThreadLocalRandom.current().nextLong(- range);
        }
        return origin + ThreadLocalRandom.current().nextLong(range);
    }


    /**
     * 0            bound = 0
     * [0, bound)   bound > 0
     * (bound, 0]   bound < 0
     */
    public static double random(double bound) {
        if (0 < bound) {
            return ThreadLocalRandom.current().nextDouble(bound);
        }
        if (bound < 0) {
            return - ThreadLocalRandom.current().nextDouble(- bound);
        }
        return 0;
    }

    /**
     * origin            bound = origin
     * [origin, bound)   bound > origin
     * (bound, origin]   bound < origin
     */
    public static double random(double origin, double bound) {
        if (origin < bound) {
            return ThreadLocalRandom.current().nextDouble(origin, bound);
        }
        if (bound < origin) {
            return origin - ThreadLocalRandom.current().nextDouble(origin - bound);
        }
        return origin;
    }

    /**
     * origin                   range = 0
     * [origin, origin + range) range > origin
     * (origin - range, origin] range < origin
     */
    public static double randomRange(double origin, double range) {
        if (0 < range) {
            return origin + ThreadLocalRandom.current().nextDouble(range);
        }
        if (range < 0) {
            return origin - ThreadLocalRandom.current().nextDouble(- range);
        }
        return origin;
    }


    /**
     * 随机token使用的字符
     */
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String token(int length, String charset) {
        int size = charset.length();
        StringBuilder sb = new StringBuilder();
        while (length-- > 0) {
            sb.append(CHARS.charAt(random(0, size)));
        }
        return sb.toString();
    }

    public static String token(int length) {
        return token(length, CHARS);
    }

    /**
     * 随机32位字符token
     * @return 32位token
     */
    public static String token() {
        return token(32, CHARS);
    }

    /**
     * 随机uid 0~Long.MAX_VALUE
     * @return uid
     */
    public static long uid() {
        return random(1L, Long.MAX_VALUE - 1);
    }


    /**
     * 随机排序
     * @param min 最小值
     * @param max 最大值
     * @return 排序后
     */
    public static int[] order(int min, int max) {
        int length = max - min + 1;
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            do {
                temp[i] = randomRange(min, length);
            } while (repeat(temp, i));
        }
        return temp;
    }

    /**
     * 是否重复
     * @param candidate 候选排序
     * @param index 第index位置和前面数字是否重复
     * @return 是否重复
     */
    private static boolean repeat(int[] candidate, int index) {
        for (int i = 0; i < index; i++) {
            if (candidate[i] == candidate[index]) {
                return true;
            }
        }
        return false;
    }
}
