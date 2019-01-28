package dive.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * 时间工具
 *
 * @author dawn
 */
public class DateUtil {

    /**
     * 毫秒计时：秒
     */
    public static final long SECOND = 1000L;

    /**
     * 毫秒计时：分
     */
    public static final long MINUTE = SECOND * 60;

    /**
     * 毫秒计时：时
     */
    public static final long HOUR = MINUTE * 60;

    /**
     * 毫秒计时：天
     */
    public static final long DAY = HOUR * 24;

    /**
     * 毫秒计时：年
     */
    public static final long YEAR = DAY * 365;

    /**
     * 毫秒计时：世纪
     */
    public static final long CENTURY = YEAR * 100;

    /**
     * 毫秒计时：千年
     */
    public static final long MILLENNIUM = CENTURY * 10;

    /**
     * 日期时间格式
     */
    public static final DateTimeFormatter DTF_DATETIME;
    static {
        DTF_DATETIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 日期格式
     */
    public static final DateTimeFormatter DTF_DATE;
    static {
        DTF_DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /**
     * 时间格式
     */
    public static final DateTimeFormatter DTF_TIME;
    static {
        DTF_TIME = DateTimeFormatter.ofPattern("HH:mm:ss");
    }
    /**
     * 标准UTC时间格式
     */
    public static final DateTimeFormatter DTF_ISO8601;
    static {
        DTF_ISO8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    }

    /**
     * 格式化时间
     *
     * @param timestamp 时间戳
     * @param zoneId 时区
     * @param formatter 格式
     * @return 格式化时间
     */
    public static String format(Long timestamp, ZoneId zoneId, DateTimeFormatter formatter) {
        Objects.requireNonNull(timestamp, "timestamp");
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(timestamp), zoneId)
                .format(formatter);
    }

    /**
     * 格式化
     * 取系统默认时区
     *
     * @param date Date
     * @param formatter 格式
     * @return 格式化
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        Objects.requireNonNull(date, "date");
        return DateUtil.format(date.getTime(), ZoneId.systemDefault(), formatter);
    }

    /**
     * 格式化日期时间
     * 系统默认时区
     *
     * @param date Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Date date) {
        return DateUtil.format(date, DTF_DATETIME);
    }

    /**
     * 格式化日期
     * 系统默认时区
     *
     * @param date Date
     * @return yyyy-MM-dd
     */
    public static String formatDate(Date date) {
        return format(date, DTF_DATE);
    }

    /**
     * 格式化时间
     * 系统默认时区
     *
     * @param date Date
     * @return HH:mm:ss
     */
    public static String formatTime(Date date) {
        return format(date, DTF_TIME);
    }

    /**
     * 格式化日期时间
     *
     * @param timestamp 时间戳
     * @param zoneId 时区
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDateTime(Long timestamp, ZoneId zoneId) {
        return format(timestamp, zoneId, DTF_DATETIME);
    }

    /**
     * 格式化日期
     *
     * @param timestamp 时间戳
     * @param zoneId 时区
     * @return yyyy-MM-dd
     */
    public static String formatDate(Long timestamp, ZoneId zoneId) {
        return format(timestamp, zoneId, DTF_DATE);
    }

    /**
     * 格式化时间
     *
     * @param timestamp 时间戳
     * @param zoneId 时区
     * @return HH:mm:ss
     */
    public static String formatTime(Long timestamp, ZoneId zoneId) {
        return format(timestamp, zoneId, DTF_TIME);
    }

    /**
     * 格式化日期时间
     *
     * @param date Date
     * @return 2048-12-19T09:56:22.845Z
     */
    public static String formatISO8601(Date date) {
        Objects.requireNonNull(date, "date");
        return LocalDateTime
                .ofInstant(date.toInstant(), ZoneOffset.UTC)
                .format(DTF_ISO8601);
    }

    /**
     * 格式化日期时间
     *
     * @param timestamp 时间戳
     * @return 2048-12-19T09:56:22.845Z
     */
    public static String formatISO8601(Long timestamp) {
        Objects.requireNonNull(timestamp, "timestamp");
        return LocalDateTime
                .ofInstant(Instant.ofEpochMilli(timestamp), ZoneOffset.UTC)
                .format(DTF_ISO8601);
    }

    //------------------------------------------------------------------------------

    /**
     * 格式化时间解析时间戳
     *
     * @param date 格式化时间
     * @param zoneOffset 时区
     * @param formatter 格式
     * @return 时间戳
     */
    public static long parse(String date, ZoneOffset zoneOffset, DateTimeFormatter formatter) {
        Objects.requireNonNull(date, "date");
        return LocalDateTime
                .parse(date, formatter)
                .toInstant(zoneOffset)
                .toEpochMilli();
    }

    /**
     * 格式化时间解析时间戳
     *
     * @param date 格式化时间
     * @param formatter 格式
     * @return 时间戳
     */
    public static long parse(String date, DateTimeFormatter formatter) {
        return parse(date, OffsetDateTime.now().getOffset(), formatter);
    }

    /**
     * 格式日期时间解析时间戳
     *
     * @param date 格式日期时间
     * @return 时间戳
     */
    public static long parseDateTime(String date) {
        return parse(date, DTF_DATETIME);
    }

    /**
     * 格式日期解析时间戳
     *
     * @param date 格式日期
     * @return 时间戳
     */
    public static long parseDate(String date) {
        return parse(date + " 00:00:00", DTF_DATETIME);
    }

    /**
     * 格式日期时间解析时间戳
     *
     * @param date 格式日期时间
     * @param zoneOffset 时区
     * @return 时间戳
     */
    public static long parseDateTime(String date, ZoneOffset zoneOffset) {
        return parse(date, zoneOffset, DTF_DATETIME);
    }

    /**
     * 格式日期解析时间戳
     *
     * @param date 格式日期
     * @param zoneOffset 时区
     * @return 时间戳
     */
    public static long parseDate(String date, ZoneOffset zoneOffset) {
        return parse(date + " 00:00:00", zoneOffset, DTF_DATETIME);
    }

}
