package test.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static dive.common.util.Util.*;

/**
 * UtilTest测试
 *
 * @author dawn
 */
public class UtilTest {

    @Test
    public void test() {
        Assert.assertTrue(empty((Object) null));
        Assert.assertTrue(exist(123));
        Assert.assertTrue(exist(123, 123));
        Assert.assertTrue(exist(123, 123, 123));
        Assert.assertTrue(exist(123, 123, 123, 123));
        Assert.assertTrue(exist(new Integer[]{123, 123, 123, 123}));
        Assert.assertFalse(exist(null));

        Assert.assertTrue(empty(null));
        Assert.assertTrue(useful("123"));
        String str1 = null;
        String str2 = "";
        Assert.assertFalse(useful(str1));
        Assert.assertFalse(useful(str2));

        List list = Collections.singletonList("123");
        List list2 = null;
        List list3 = Collections.emptyList();
        Assert.assertTrue(useful(list));
        Assert.assertFalse(useful(list2));
        Assert.assertFalse(useful(list3));

        Map<String, String> map = new HashMap<>();
        map.put("123", "456");
        Map map2 = null;
        Map map3 = new HashMap();
        Assert.assertTrue(useful(map));
        Assert.assertFalse(useful(map2));
        Assert.assertFalse(useful(map3));

        Boolean bool = true;
        Boolean bool2 = null;
        Boolean bool3 = false;
        Assert.assertTrue(truer(bool));
        Assert.assertFalse(truer(bool2));
        Assert.assertFalse(truer(bool3));
    }

}
