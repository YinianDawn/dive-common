package dive.common.util;

import java.util.Collection;
import java.util.Map;

/**
 * 工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class Util {

    /**
     * 是否为空
     *
     * @param check 对象
     * @return 是否为空
     */
    public static boolean empty(Object check) {
        return null == check;
    }

    /**
     * 是否存在（不为空）
     *
     * @param check 对象
     * @return 是否存在
     */
    public static boolean exist(Object check) {
        return null != check;
    }

    public static boolean exist(Object check, Object check2) {
        return exist(check) && exist(check2);
    }

    public static boolean exist(Object check, Object check2, Object check3) {
        return exist(check) && exist(check2) && exist(check3);
    }

    public static boolean exist(Object check, Object... checks) {
        for (Object c : checks) {
            if (empty(c)) {
                return false;
            }
        }
        return exist(check);
    }

    public static boolean exist(Object[] checks) {
        if (empty(checks)) {
            return false;
        }
        for (Object c : checks) {
            if (empty(c)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 是否为空或空串
     *
     * @param check 字符串
     * @return 是否为空或字符串
     */
    public static boolean empty(String check) {
        return null == check || check.isEmpty();
    }

    /**
     * 是否不为空或不是空串
     *
     * @param check 字符串
     * @return 是否不为空或不是空串
     */
    public static boolean useful(String check) {
        return null != check && !check.isEmpty();
    }

    public static boolean useful(String check, String check2) {
        return useful(check) && useful(check2);
    }

    public static boolean useful(String check, String check2, String check3) {
        return useful(check) && useful(check2) && useful(check3);
    }

    public static boolean useful(String check, String... checks) {
        for (String c : checks) {
            if (empty(c)) {
                return false;
            }
        }
        return useful(check);
    }

    public static boolean useful(String[] checks) {
        if (empty(checks)) {
            return false;
        }
        for (String c : checks) {
            if (empty(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean useful(Object[] checks) {
        if (empty(checks)) {
            return false;
        }
        return 0 != checks.length;
    }

    /**
     * 集合是否不为空且有元素
     *
     * @param check 集合
     * @return 集合是否不为空且有元素
     */
    public static boolean useful(Collection check) {
        return null != check && 0 < check.size();
    }

    /**
     * 集合是否不为空且有元素
     *
     * @param check 集合
     * @return 集合是否不为空且有元素
     */
    public static boolean useful(Map check) {
        return null != check && 0 < check.size();
    }

    /**
     * 是否不为空且为真
     *
     * @param check 布尔值
     * @return 是否不为空且为真
     */
    public static boolean truer(Boolean check) {
        return null != check && check;
    }

}
