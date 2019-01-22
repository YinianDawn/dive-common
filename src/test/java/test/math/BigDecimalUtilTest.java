package test.math;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static dive.common.math.BigDecimalUtil.*;

/**
 * RandomUtil测试
 *
 * @author dawn
 * @date 2019/1/19 23:03
 */
public class BigDecimalUtilTest {

    @Test
    public void test() {
        BigDecimal NEGATIVE = new BigDecimal("-1");
        BigDecimal ZERO = BigDecimal.ZERO;
        BigDecimal ONE = new BigDecimal("1");
        BigDecimal TWO = new BigDecimal("2");
        BigDecimal THREE = new BigDecimal("3");

        Assert.assertEquals(ONE, add(ONE, ZERO));
        Assert.assertEquals(ONE, sub(TWO, ONE));
        Assert.assertEquals(TWO, mul(TWO, ONE));
        Assert.assertEquals(TWO.setScale(8, BigDecimal.ROUND_HALF_UP), div(TWO, ONE));
        Assert.assertEquals(TWO, div(TWO, ONE, 0));
        Assert.assertEquals(TWO, div(TWO, ONE, 0, RoundingMode.HALF_EVEN));

        Assert.assertEquals(ONE, rem(THREE, TWO));

        Assert.assertEquals(THREE, sum(ZERO, ONE, TWO));
        Assert.assertEquals(TWO.setScale(8, RoundingMode.HALF_UP), avg(ONE, THREE));
        Assert.assertEquals(TWO.setScale(8, RoundingMode.HALF_UP), avg(8, ONE, THREE));
        Assert.assertEquals(TWO.setScale(8, RoundingMode.HALF_UP), avg(8, RoundingMode.HALF_UP, ONE, THREE));
        Assert.assertEquals(NEGATIVE, neg(ONE));

        Assert.assertEquals(ONE, abs(NEGATIVE));
        Assert.assertEquals(ONE, abs(NEGATIVE, ZERO));
        Assert.assertEquals(ONE, abs(ONE, TWO));
        Assert.assertNotEquals(TWO, abs(ONE, TWO));

        Assert.assertEquals(THREE, max(ONE, ZERO, THREE));
        Assert.assertEquals(ZERO, min(ONE, ZERO, THREE));

        Assert.assertTrue(positive(ONE));
        Assert.assertFalse(positive(ONE.negate()));

        Assert.assertFalse(negative(ONE));
        Assert.assertTrue(negative(ONE.negate()));

        Assert.assertEquals(new BigDecimal("1"), reciprocate(ONE, 0));
        Assert.assertEquals(new BigDecimal("0.01"), minimum(2));

        Assert.assertTrue(greater(TWO, ONE));
        Assert.assertFalse(greater(TWO, TWO));

        Assert.assertTrue(greaterOrEqual(TWO, ONE));
        Assert.assertTrue(greaterOrEqual(TWO, TWO));
        Assert.assertFalse(greaterOrEqual(ONE, TWO));

        Assert.assertTrue(less(ONE, TWO));
        Assert.assertFalse(less(ONE, ONE));

        Assert.assertTrue(lessOrEqual(ONE, TWO));
        Assert.assertTrue(lessOrEqual(ONE, ONE));
        Assert.assertFalse(lessOrEqual(TWO, ONE));

        Assert.assertTrue(equal(ONE, ONE));
        Assert.assertFalse(equal(ONE, TWO));

    }

}
