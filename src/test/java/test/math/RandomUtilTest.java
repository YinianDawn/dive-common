package test.math;

import org.junit.Assert;
import org.junit.Test;

import static dive.common.math.RandomUtil.*;

/**
 * RandomUtil测试
 *
 * @author dawn
 */
public class RandomUtilTest {

    @Test
    public void test() {
        int nextInt;
        long nextLong;

        double nextDouble = nextDouble();
        Assert.assertTrue(0 <= nextDouble);
        Assert.assertTrue(nextDouble < 1);

        nextInt = random(5);
        Assert.assertTrue(0 <= nextInt);
        Assert.assertTrue(nextInt < 5);

        nextInt = random(6, 4);
        Assert.assertTrue(4 < nextInt);
        Assert.assertTrue(nextInt <= 6);
        nextInt = randomRange(6, -4);
        Assert.assertTrue(2 < nextInt);
        Assert.assertTrue(nextInt <= 6);

        nextLong = random(5L);
        Assert.assertTrue(0 <= nextLong);
        Assert.assertTrue(nextLong < 5);
        nextLong = random(6L, 4L);
        Assert.assertTrue(4 < nextLong);
        Assert.assertTrue(nextLong <= 6);
        nextLong = randomRange(6L, -4L);
        Assert.assertTrue(2 < nextLong);
        Assert.assertTrue(nextLong <= 6);

        nextDouble = random(5D);
        Assert.assertTrue(0 <= nextDouble);
        Assert.assertTrue(nextDouble < 5);
        nextDouble = random(6D, 4D);
        Assert.assertTrue(4 < nextDouble);
        Assert.assertTrue(nextDouble <= 6);
        nextDouble = randomRange(6D, -4D);
        Assert.assertTrue(2 < nextDouble);
        Assert.assertTrue(nextDouble <= 6);

    }

}
