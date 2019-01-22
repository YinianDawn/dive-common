package dive.common.util;

import java.util.function.*;

/**
 * 重试
 *
 * @author dawn
 * @date 2019/1/22 0:57
 */
public class TryUtil {

    /**
     * 默认次数
     */
    public static int DEFAULT_TIMES = 0;

    /**
     * 重试直到返回非空对象
     *
     * @param supplier 生产者
     * @param times 重试次数
     * @param runnable 失败后执行
     * @param <R> 返回对象
     * @return 非空对象
     */
    public static <R> R retry(Supplier<R> supplier, int times, Runnable runnable) {
        R r = null;
        if (times == 0) {
            // once
            r = supplier.get();
        } else if (times < 0) {
            // infinite
            while (null == r) {
                r = supplier.get();
                if (null == r && null != runnable) {
                    runnable.run();
                }
            }
        } else {
            // times
            while (times-- > -1 && null == r) {
                r = supplier.get();
                if (null == r && null != runnable) {
                    runnable.run();
                }
            }
        }
        return r;
    }

    public static <R> R retry(Supplier<R> supplier, int times) {
        return retry(supplier, times, null);
    }

    public static <R> R retry(Supplier<R> supplier, Runnable runnable) {
        return retry(supplier, DEFAULT_TIMES, runnable);
    }

    public static <R> R retry(Supplier<R> supplier) {
        return retry(supplier, DEFAULT_TIMES, null);
    }

    /**
     * 重试直到返回非空对象
     *
     * @param function 转换者
     * @param t 参数
     * @param times 次数
     * @param consumer 消费者
     * @param <T> 参数类型
     * @param <R> 结果类型
     * @return 结果
     */
    public static <T, R> R retry(Function<T, R> function, T t, int times, Consumer<T> consumer) {
        R r = null;
        if (times == 0) {
            // once
            r = function.apply(t);
        } else if (times < 0) {
            // infinite
            while (null == r) {
                r = function.apply(t);
                if (null != r && null != consumer) {
                    consumer.accept(t);
                }
            }
        } else {
            // times
            while (times-- > -1 && null == r) {
                r = function.apply(t);
                if (null != r && null != consumer) {
                    consumer.accept(t);
                }
            }
        }
        return r;
    }

    public static <T, R> R retry(Function<T, R> function, T t, int times) {
        return retry(function, t, times, null);
    }

    public static <T, R> R retry(Function<T, R> function, T t, Consumer<T> consumer) {
        return retry(function, t, DEFAULT_TIMES, consumer);
    }

    public static <T, R> R retry(Function<T, R> function, T t) {
        return retry(function, t, DEFAULT_TIMES, null);
    }

    /**
     * 重试直到返回非空对象
     *
     * @param biFunction 转换者
     * @param t 参数
     * @param u 参数
     * @param times 次数
     * @param biConsumer 消费者
     * @param <T> 参数类型
     * @param <U> 参数类型
     * @param <R> 结果类型
     * @return 结果
     */
    public static <T, U, R> R retry(BiFunction<T, U, R> biFunction, T t, U u, int times, BiConsumer<T, U> biConsumer) {
        R r = null;
        if (times == 0) {
            // once
            r = biFunction.apply(t, u);
        } else if (times < 0) {
            // infinite
            while (null == r) {
                r = biFunction.apply(t, u);
                if (null != r && null != biConsumer) {
                    biConsumer.accept(t, u);
                }
            }
        } else {
            // times
            while (times-- > -1 && null == r) {
                r = biFunction.apply(t, u);
                if (null != r && null != biConsumer) {
                    biConsumer.accept(t, u);
                }
            }
        }
        return r;
    }

    public static <T, U, R> R retry(BiFunction<T, U, R> biFunction, T t, U u, int times) {
        return retry(biFunction, t, u, times, null);
    }

    public static <T, U, R> R retry(BiFunction<T, U, R> biFunction, T t, U u, BiConsumer<T, U> biConsumer) {
        return retry(biFunction, t, u, DEFAULT_TIMES, biConsumer);
    }

    public static <T, U, R> R retry(BiFunction<T, U, R> biFunction, T t, U u) {
        return retry(biFunction, t, u, DEFAULT_TIMES, null);
    }
}
