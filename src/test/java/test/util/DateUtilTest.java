package test.util;

import dive.common.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.TimeZone;

/**
 * DateUtil测试
 *
 * @author dawn
 */
public class DateUtilTest {

    @Test
    public void test() throws ParseException {

        Assert.assertEquals(1000L, DateUtil.SECOND);
        Assert.assertEquals(1000L * 60, DateUtil.MINUTE);
        Assert.assertEquals(1000L * 60 * 60, DateUtil.HOUR);
        Assert.assertEquals(1000L * 60 * 60 * 24, DateUtil.DAY);
        Assert.assertEquals(1000L * 60 * 60 * 24 * 365, DateUtil.YEAR);
        Assert.assertEquals(1000L * 60 * 60 * 24 * 365 * 100, DateUtil.CENTURY);
        Assert.assertEquals(1000L * 60 * 60 * 24 * 365 * 100 * 10, DateUtil.MILLENNIUM);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        // 按默认时区GMT+8进行转换时间
        Date date = format.parse("2018-11-07 06:10:13.879");

        Assert.assertEquals("2018-11-07 07:10:13", DateUtil.format(date.getTime(), ZoneId.of("+9"), DateUtil.DTF_DATETIME));
        Assert.assertEquals("2018-11-07", DateUtil.format(date.getTime(), ZoneId.of("+9"), DateUtil.DTF_DATE));
        Assert.assertEquals("07:10:13", DateUtil.format(date.getTime(), ZoneId.of("+9"), DateUtil.DTF_TIME));
        Assert.assertEquals("2018-11-07T07:10:13.879Z", DateUtil.format(date.getTime(), ZoneId.of("+9"), DateUtil.DTF_ISO8601));
        Assert.assertEquals("2018-11-07T06:10:13.879Z", DateUtil.format(date, DateUtil.DTF_ISO8601));

        Assert.assertEquals("2018-11-07 06:10:13", DateUtil.formatDateTime(date));
        Assert.assertEquals("2018-11-07", DateUtil.formatDate(date));
        Assert.assertEquals("06:10:13", DateUtil.formatTime(date));

        Assert.assertEquals("2018-11-06 14:10:13", DateUtil.formatDateTime(date.getTime(), ZoneId.of("GMT-8")));
        Assert.assertEquals("2018-11-06", DateUtil.formatDate(date.getTime(), ZoneId.of("GMT-8")));
        Assert.assertEquals("14:10:13", DateUtil.formatTime(date.getTime(), ZoneId.of("GMT-8")));

        Assert.assertEquals("2018-11-06T22:10:13.879Z", DateUtil.formatISO8601(date));
        Assert.assertEquals("2018-11-06T22:10:13.879Z", DateUtil.formatISO8601(date.getTime()));

        //-------------------------------------

        format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        // 按时区UTC进行转换时间
        date = format.parse("2018-11-07 06:10:13.879");

        Assert.assertEquals(1541571013879L, date.getTime());
        Assert.assertEquals(1541571013879L, DateUtil.parse("2018-11-07T06:10:13.879Z", ZoneOffset.UTC, DateUtil.DTF_ISO8601));
        Assert.assertEquals(1541571013000L, DateUtil.parse("2018-11-07 14:10:13", DateUtil.DTF_DATETIME));
        Assert.assertEquals(1541571013000L, DateUtil.parseDateTime("2018-11-07 14:10:13"));
        Assert.assertEquals(1541571013000L - DateUtil.HOUR * 14 - DateUtil.MINUTE * 10 - DateUtil.SECOND * 13, DateUtil.parseDate("2018-11-07"));

        Assert.assertEquals(1541571013000L - DateUtil.HOUR * 8, DateUtil.parseDateTime("2018-11-07 06:10:13", ZoneOffset.ofHours(8)));
        Assert.assertEquals(1541571013000L - DateUtil.HOUR * 6 - DateUtil.MINUTE * 10 - DateUtil.SECOND * 13 - DateUtil.HOUR * 8, DateUtil.parseDate("2018-11-07", ZoneOffset.ofHours(8)));

    }

}
