package focus.focusstepone.focustest.highquality;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;

import java.util.Locale;

/**
 * focus Create in 11:33 2018/8/27
 */
public class JodaDateTest {
    public static void main(String[] args) {
        //当前时间戳
        DateTime dateTime = new DateTime();
        System.out.println(dateTime);
        System.out.println(dateTime.dayOfWeek().getAsText(Locale.CHINA));//输出中文星期
        System.out.println(dateTime.toLocalDate());//日期格式化
        System.out.println(dateTime.plusHours(100).dayOfWeek().getAsText(Locale.CHINA));//加100小时以后是星期几
        System.out.println(dateTime.dayOfWeek().getAsText(Locale.CHINA));
        System.out.println(dateTime.plusDays(100).toLocalDate());//100天后的日期
        System.out.println(dateTime.minusYears(10).dayOfWeek().getAsText());//10年前的今天是星期几
        System.out.println(Hours.hoursBetween(dateTime, new DateTime("2020-10-20")).getHours());//两个日期相差的小时数
        System.out.println(dateTime.withZone(DateTimeZone.forOffsetMillis(24*60*60*8)).toDateTime());
    }

}


