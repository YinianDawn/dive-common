package test.util;

import dive.common.util.Streams;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streams测试
 *
 * @author dawn
 */
public class StreamsTest {

    @Test
    public void test() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        List<Stream<String>> streams =
                Streams.split(list.stream(), 2)
                        .collect(Collectors.toList());
        Assert.assertEquals(3, streams.size());
        Assert.assertEquals(1, streams.get(2).count());

        streams = Streams.split(list.stream(), 5)
                .collect(Collectors.toList());
        Assert.assertEquals(1, streams.size());
        Assert.assertEquals(5, streams.get(0).count());

        streams = Streams.split(list.stream(), 3)
                .collect(Collectors.toList());
        Assert.assertEquals(2, streams.size());
        Assert.assertEquals(3, streams.get(0).count());
        Assert.assertEquals(2, streams.get(1).count());
    }

}
