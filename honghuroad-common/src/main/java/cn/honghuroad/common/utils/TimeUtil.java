package cn.honghuroad.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by chenzq
 * Date: 2019/3/1 上午1:10
 **/
public class TimeUtil {
    public static final Date DATE_AS_NULL = new Date(1L);

    public TimeUtil() {
    }

    public static Calendar getCalendar() {
        return getCalendar((Date)null);
    }

    public static Calendar getCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        if(date != null) {
            cal.setTime(date);
        }

        return cal;
    }

    public static int nextInt(int dateId) {
        Date date = parse(dateId);
        return date != null?toInt(new Date(date.getTime() + 86400000L)):0;
    }

    public static int prevInt(int dateId) {
        Date date = parse(dateId);
        return date != null?toInt(new Date(date.getTime() - 86400000L)):0;
    }

    public static int toSeconds() {
        return (int)(System.currentTimeMillis() / 1000L);
    }

    public static int toSeconds(Date date) {
        return date != null?(int)(date.getTime() / 1000L):0;
    }

    public static String toString(Date date) {
        return toString((String)null, date);
    }

    public static String toString(String pattern, Date date) {
        return (new SimpleDateFormat(pattern == null?"yyyy-MM-dd HH:mm:ss":pattern)).format(date == null?new Date():date);
    }

    private static Calendar floor(Calendar cal) {
        cal.set(11, 0);
        cal.set(12, 0);
        cal.set(13, 0);
        cal.set(14, 0);
        return cal;
    }

    public static Date floor() {
        return floor((Date)null, 0);
    }

    public static Date floor(int delta) {
        return floor((Date)null, delta);
    }

    public static Date floor(Date date) {
        return floor(date, 0);
    }

    public static Date floor(Date date, int delta) {
        Calendar cal = floor(getCalendar(date));
        if(delta != 0) {
            cal.add(5, delta);
        }

        return cal.getTime();
    }

    public static long getMillisOfDay(Date date, boolean returnMaxIfNull) {
        if(date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            long timestamp = 0L;
            timestamp += (long)(cal.get(11) * 60 * 60) * 1000L;
            timestamp += (long)(cal.get(12) * 60) * 1000L;
            timestamp += (long)cal.get(13) * 1000L;
            timestamp += (long)cal.get(14);
            return timestamp;
        } else {
            return returnMaxIfNull?86400000L:0L;
        }
    }

    private static Calendar ceiling(Calendar cal) {
        cal.set(11, 23);
        cal.set(12, 59);
        cal.set(13, 59);
        cal.set(14, 999);
        return cal;
    }

    public static Date ceiling() {
        return ceiling((Date)null, 0);
    }

    public static Date ceiling(int delta) {
        return ceiling((Date)null, delta);
    }

    public static Date ceiling(Date date) {
        return ceiling(date, 0);
    }

    public static Date ceiling(Date date, int delta) {
        Calendar cal = ceiling(getCalendar(date));
        if(delta != 0) {
            cal.add(5, delta);
        }

        return cal.getTime();
    }

    public static Date delta(int days) {
        return delta((long)days, TimeUnit.DAYS);
    }

    public static Date delta(long duration, TimeUnit timeUnit) {
        if(timeUnit == null) {
            timeUnit = TimeUnit.DAYS;
        }

        return new Date(System.currentTimeMillis() + timeUnit.toMillis(duration));
    }

    public static Date toMonthFirst() {
        return toMonthFirst(new Date());
    }

    public static Date toMonthFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        c.set(5, 1);
        return c.getTime();
    }

    public static Date toMonthLast() {
        return toMonthLast(new Date());
    }

    public static Date toMonthLast(Date date) {
        Calendar c = ceiling(getCalendar(date));
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static Date toNextMonthFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        c.set(5, 1);
        c.add(2, 1);
        return c.getTime();
    }

    public static Date toNextMonthLast(Date date) {
        Calendar c = ceiling(getCalendar(date));
        c.set(5, 1);
        c.add(2, 1);
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static Date toPrevMonthFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        c.set(5, 1);
        c.add(2, -1);
        return c.getTime();
    }

    public static Date toPrevMonthLast(Date date) {
        Calendar c = ceiling(getCalendar(date));
        c.set(5, 1);
        c.add(2, -1);
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static int toInt() {
        return toInt(new Date());
    }

    public static int toInt(Date date) {
        return date != null?Integer.parseInt(format(date, "yyyyMMdd")):0;
    }

    public static int toMonthInt(Date date) {
        if(date == null) {
            date = new Date();
        }

        return Integer.parseInt(format(date, "yyyyMM"));
    }

    public static Date parse(int dateId) {
        if(dateId > 0) {
            try {
                return (new SimpleDateFormat("yyyyMMdd")).parse(String.valueOf(dateId));
            } catch (Throwable var2) {
                ;
            }
        }

        return null;
    }

    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String format(Date date, String formatString) {
        if(date == null) {
            return "";
        }

        return (new SimpleDateFormat(formatString)).format(date);
    }

    public static Date parse(String dateString) {
        if(dateString != null) {
            SimpleDateFormat dateFormat = getDateFormatByDateString(dateString);
            if(dateFormat != null) {
                try {
                    return dateFormat.parse(dateString);
                } catch (ParseException var3) {
                    ;
                }
            }
        }

        return null;
    }

    public static SimpleDateFormat getDateFormatByDateString(String dateString) {
        if(dateString != null) {
            if(dateString.length() == "yyyyMMdd".length()) {
                return new SimpleDateFormat("yyyyMMdd");
            }

            if(dateString.length() == "yyyy-MM-dd".length()) {
                return new SimpleDateFormat("yyyy-MM-dd");
            }

            if(dateString.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }

            if(dateString.length() < "yyyy-MM-dd HH:mm:ss".length()) {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss".substring(0, dateString.length()));
            }
        }

        return null;
    }

    public static int getYearFrom(int dateInt) {
        return dateInt > 10000000 && dateInt < 99999999?Calendar.getInstance().get(1) - dateInt / 10000:0;
    }

    public static int getYearFrom(Date date) {
        return date != null?getYearFrom(toInt(date)):0;
    }

    public static Date toWeekFirst(Date date) {
        Calendar c = floor(getCalendar(date));
        int dayOfWeek = c.get(7);
        if(dayOfWeek != 2) {
            if(dayOfWeek == 1) {
                c.add(5, -6);
            } else {
                c.add(5, 2 - dayOfWeek);
            }
        }

        return c.getTime();
    }

    public static boolean checkOverOneYear(Date begin, Date end) {
        if (begin != null && end != null && !end.before(begin)) {
            //begin是floor的 end是ceil的 加一天
            if (end.getTime() - begin.getTime() > (366 + 1) * 24 * 3600000L) {
                return true;
            }
        }
        return false;
    }

    public static Date toWeekend(Date date) {
        Calendar c = ceiling(getCalendar(date));
        int dayOfWeek = c.get(7);
        if(dayOfWeek != 1) {
            c.add(5, 8 - dayOfWeek);
        }

        return c.getTime();
    }


    public static Date getPreOneDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getPreOneWeek() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(4, -1);
        return calendar.getTime();
    }

    public static Date getPreOneMonth() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(2, -1);
        return calendar.getTime();
    }

    public static class JsonDateSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(TimeUtil.format(date, ConstStrings.DATE_FORMAT_PATTERN_SHORT));
        }
    }

    public static class JsonDateMinSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Date> {
        @Override
        public void serialize(Date value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(TimeUtil.format(value, ConstStrings.DATE_FORMAT_PATTERN_MIDDLE));
        }
    }

    public static class JsonDateSecSerializer extends com.fasterxml.jackson.databind.JsonSerializer<Date> {
        @Override
        public void serialize(Date value, com.fasterxml.jackson.core.JsonGenerator gen, com.fasterxml.jackson.databind.SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(TimeUtil.format(value, ConstStrings.DATE_FORMAT_PATTERN_SEC));
        }
    }

    public static class JsonToMillisSerializer extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeNumber(value.getTime());
            }
        }
    }

    public static class MillisToDateDeserializer extends JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            long millis = p.getLongValue();
            return new Date(millis);
        }
    }

    public static class JsonDateDeserializer extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
            return TimeUtil.parse(jp.getText());
        }
    }

    public static String getDiffBetweenDate(Date begin, Date end) {
        if (begin != null && end != null) {
            StringBuilder diffDate = new StringBuilder();
            long nh = 1000 * 60 * 60;//一小时的毫秒数
            long nm = 1000 * 60;//一分钟的毫秒数
            long diff = end.getTime() - begin.getTime();
            long hour = diff / nh;//计算差多少小时
            if (hour > 0) {
                if(hour<10){
                    diffDate.append("0");
                }
                diffDate.append(hour);
                diffDate.append(":");
            }else{
                diffDate.append("00:");
            }
            long min = diff % nh / nm;//计算差多少分钟
            if (min > 0) {
                if(min<10){
                    diffDate.append("0");
                }
                diffDate.append(min);
            }else {
                diffDate.append("00");
            }
            return diffDate.toString();
        }
        return "";
    }

    public static int getDifferMinute(Date date, Date anotherAfterDate) {
        if (date != null && anotherAfterDate != null) {
            long diff = anotherAfterDate.getTime() - date.getTime();
            Date d = new Date(diff);
            long minute = diff % (60 * 1000) > 0 ? (diff / (60 * 1000)) + 1 : diff / (60 * 1000);
            return (int) minute;
        }
        return 0;
    }

    public static boolean isToday(Date date) {
        if (date != null) {
            return toInt(new Date()) == toInt(date);
        }
        return false;
    }
    public final static int ERA = 0;
    public final static int YEAR = 1;
    public final static int DAY_OF_YEAR = 6;

    public static boolean isSameDay(Date date1, Date date2) {
        if(date1 != null && date2 != null) {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            return isSameDay(cal1, cal2);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(ERA) == cal2.get(ERA) && cal1.get(YEAR) == cal2.get(YEAR) && cal1.get(DAY_OF_YEAR) == cal2.get(DAY_OF_YEAR);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }
}
