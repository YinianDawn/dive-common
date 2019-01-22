package dive.common.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

/**
 * 数学计算工具
 *
 * @author dawn
 * @date 2019/1/19 23:00
 */
public class BigDecimalUtil {

    public static final BigDecimal ZERO = BigDecimal.ZERO;
    public static final BigDecimal ONE = BigDecimal.ONE;
    public static final BigDecimal TWO = new BigDecimal("2");
    public static final BigDecimal TEN = BigDecimal.TEN;
    public static final BigDecimal HUNDRED = new BigDecimal("100");
    public static final BigDecimal THOUSAND = new BigDecimal("1000");

    /**
     * 加法
     * @param augend 被加数
     * @param addend 加数
     * @return 和
     */
    public static BigDecimal add(BigDecimal augend, BigDecimal addend) {
        Objects.requireNonNull(augend, "augend");
        Objects.requireNonNull(addend, "addend");
        return augend.add(addend);
    }

    /**
     * 减法
     * @param minuend 被减数
     * @param subtrahend 减数
     * @return 差
     */
    public static BigDecimal sub(BigDecimal minuend, BigDecimal subtrahend) {
        Objects.requireNonNull(minuend, "minuend");
        Objects.requireNonNull(subtrahend, "subtrahend");
        return minuend.subtract(subtrahend);
    }

    /**
     * 乘法
     * @param multiplicand 被乘数
     * @param multiplier 乘数
     * @return 积
     */
    public static BigDecimal mul(BigDecimal multiplicand, BigDecimal multiplier) {
        Objects.requireNonNull(multiplicand, "multiplicand");
        Objects.requireNonNull(multiplier, "multiplier");
        return multiplicand.multiply(multiplier);
    }

    /**
     * 除法
     * @param dividend 被除数
     * @param divisor 除数
     * @param scale 精度
     * @param roundingMode 截断模式
     * @return 商
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor, int scale, RoundingMode roundingMode) {
        Objects.requireNonNull(dividend, "dividend");
        Objects.requireNonNull(divisor, "divisor");
        return dividend.divide(divisor, scale, roundingMode);
    }

    /**
     * 除法 四舍五入
     * @param dividend 被除数
     * @param divisor 除数
     * @param scale 精度
     * @return 商
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor, int scale) {
        return div(dividend, divisor, scale, RoundingMode.HALF_UP);
    }

    /**
     * 除法 8位精度 四舍五入
     * @param dividend 被除数
     * @param divisor 除数
     * @return 商
     */
    public static BigDecimal div(BigDecimal dividend, BigDecimal divisor) {
        return div(dividend, divisor, 8, RoundingMode.HALF_UP);
    }

    /**
     * 模运算
     * @param dividend 被除数
     * @param divisor 除数
     * @return 余数
     */
    public static BigDecimal rem(BigDecimal dividend, BigDecimal divisor) {
        Objects.requireNonNull(dividend, "dividend");
        Objects.requireNonNull(divisor, "divisor");
        return dividend.remainder(divisor);
    }

    /**
     * 多个加数求和
     * @param addend 加数
     * @return 和
     */
    public static BigDecimal sum(BigDecimal... addend) {
        Objects.requireNonNull(addend, "addend");
        for (BigDecimal a : addend) {
            Objects.requireNonNull(a, "addend");
        }
        return Arrays.stream(addend).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 平均数
     * @param scale 精度
     * @param roundingMode 截断模式
     * @param addend 加数
     * @return 平均数
     */
    public static BigDecimal avg(int scale, RoundingMode roundingMode, BigDecimal... addend) {
        Objects.requireNonNull(addend, "addend");
        for (BigDecimal a : addend) {
            Objects.requireNonNull(a, "addend");
        }
        if (0 == addend.length) {
            return ZERO;
        }
        BigDecimal sum = Arrays.stream(addend)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(BigDecimal.valueOf(addend.length), scale, roundingMode);
    }

    /**
     * 平均数 四舍五入
     * @param scale 精度
     * @param addend 加数
     * @return 平均数
     */
    public static BigDecimal avg(int scale, BigDecimal... addend) {
        return avg(scale, RoundingMode.HALF_UP, addend);
    }

    /**
     * 平均数 8位精度 四舍五入
     * @param addend 加数
     * @return 平均数
     */
    public static BigDecimal avg(BigDecimal... addend) {
        return avg(8, RoundingMode.HALF_UP, addend);
    }

    /**
     * 相反数
     * @param number 数字
     * @return 相反数
     */
    public static BigDecimal neg(BigDecimal number) {
        Objects.requireNonNull(number, "number");
        return number.negate();
    }

    /**
     * 绝对值
     * @param number 数字
     * @return 绝对值
     */
    public static BigDecimal abs(BigDecimal number) {
        Objects.requireNonNull(number, "number");
        return number.abs();
    }

    /**
     * 差的绝对值
     * @param number1 数1
     * @param number2 数2
     * @return 差的绝对值
     */
    public static BigDecimal abs(BigDecimal number1, BigDecimal number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        return number1.subtract(number2).abs();
    }

    /**
     * 检查数字
     * @param numbers 数字
     */
    private static void checkNumbers(BigDecimal[] numbers) {
        Objects.requireNonNull(numbers, "numbers");
        for (BigDecimal n : numbers) {
            Objects.requireNonNull(n, "numbers");
        }
        if (numbers.length == 0) {
            throw new RuntimeException("the length of numbers can not be 0");
        }
    }

    /**
     * 最大值
     * @param numbers 数
     * @return 最大值
     */
    public static BigDecimal max(BigDecimal... numbers) {
        BigDecimalUtil.checkNumbers(numbers);
        return Arrays.stream(numbers).max(BigDecimal::compareTo).orElse(null);
    }

    /**
     * 最小值
     * @param numbers 数
     * @return 最小值
     */
    public static BigDecimal min(BigDecimal... numbers) {
        BigDecimalUtil.checkNumbers(numbers);
        return Arrays.stream(numbers).min(BigDecimal::compareTo).orElse(null);
    }


    /**
     * 是否正数
     * @param number 数
     * @return 是否正数
     */
    public static boolean positive(BigDecimal number) {
        Objects.requireNonNull(number, "number");
        return 0 < number.compareTo(ZERO);
    }

    /**
     * 是否负数
     * @param number 数
     * @return 是否负数
     */
    public static boolean negative(BigDecimal number) {
        Objects.requireNonNull(number, "number");
        return number.compareTo(ZERO) < 0;
    }


    /**
     * 倒数
     * @param number 数
     * @param scale 精度
     * @param roundingMode 截断模式
     * @return 倒数
     */
    public static BigDecimal reciprocate(BigDecimal number, int scale, RoundingMode roundingMode) {
        Objects.requireNonNull(number, "number");
        if (0 == number.compareTo(ZERO)) {
            throw new RuntimeException("can not find the reciprocal of number: " + number);
        }
        return ONE.divide(number, scale, roundingMode);
    }

    /**
     * 倒数 四舍五入
     * @param number 数
     * @param scale 精度
     * @return 倒数
     */
    public static BigDecimal reciprocate(BigDecimal number, int scale) {
        return reciprocate(number, scale, RoundingMode.HALF_UP);
    }

    /**
     * 倒数 8位精度 四舍五入
     * @param number 数
     * @return 倒数
     */
    public static BigDecimal reciprocate(BigDecimal number) {
        return reciprocate(number, 8, RoundingMode.HALF_UP);
    }

    /**
     * 对应精度最小步长
     * @param scale 精度
     * @return 最小步长
     */
    public static BigDecimal minimum(int scale) {
        if (scale == 0) {
            return ONE;
        }
        StringBuilder sb = new StringBuilder("1");
        if (0 < scale) {
            while (0 < scale--) {
                sb.insert(0, "0");
            }
            return new BigDecimal(sb.replace(0, 1, "0.").toString());
        }
        while (scale++ < 0) {
            sb.append("0");
        }
        return new BigDecimal(sb.toString());
    }

    /**
     * 精度
     * @param number 数字
     * @return 精度
     */
    public static int scale(BigDecimal number) {
        Objects.requireNonNull(number, "number");
        return number.scale();
    }


    /**
     * 大于
     * @param number1 数1
     * @param number2 数2
     * @return 是否大于
     */
    public static boolean greater(BigDecimal number1, BigDecimal number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        return 0 < number1.compareTo(number2);
    }

    /**
     * 大于等于
     * @param number1 数1
     * @param number2 数2
     * @return 是否大于或等于
     */
    public static boolean greaterOrEqual(BigDecimal number1, BigDecimal number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        return 0 <= number1.compareTo(number2);
    }

    /**
     * 小于
     * @param number1 数1
     * @param number2 数2
     * @return 是否小于
     */
    public static boolean less(BigDecimal number1, BigDecimal number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        return number1.compareTo(number2) < 0;
    }

    /**
     * 小于等于
     * @param number1 数1
     * @param number2 数2
     * @return 是否小于或等于
     */
    public static boolean lessOrEqual(BigDecimal number1, BigDecimal number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        return number1.compareTo(number2) <= 0;
    }

    /**
     * 相等
     * @param number1 数1
     * @param number2 数2
     * @return 是否相等
     */
    public static boolean equal(BigDecimal number1, BigDecimal number2) {
        Objects.requireNonNull(number1, "number1");
        Objects.requireNonNull(number2, "number2");
        return 0 == number1.compareTo(number2);
    }

}
