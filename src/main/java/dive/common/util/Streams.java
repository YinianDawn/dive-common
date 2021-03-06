package dive.common.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流工具
 *
 * @author dawn
 */
public class Streams {

    /**
     * 分割流
     * @param stream 流
     * @param max 最大个数
     * @param <T> 分割基本类型
     * @return 分割后的流
     */
    public static <T> Stream<Stream<T>> split(Stream<T> stream, int max) {
        List<T> list = stream.collect(Collectors.toList());
        int limit = list.size() / max;
        if (0 != list.size() % max) {
            limit++;
        }
        return Stream.iterate(0, i -> i + 1)
                .limit(limit)
                .map(i -> list.stream().skip(i * max).limit(max));
    }

    /**
     * 分割列表
     * @param list 列表
     * @param max 最大个数
     * @param <T> 分割基本类型
     * @return 分割后的列表的列表
     */
    public static <T> List<List<T>> split(List<T> list, int max) {
        int limit = list.size() / max;
        if (0 != list.size() % max) {
            limit++;
        }
        return Stream.iterate(0, i -> i + 1)
                .limit(limit)
                .map(i -> list.stream()
                        .skip(i * max)
                        .limit(max)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

}
