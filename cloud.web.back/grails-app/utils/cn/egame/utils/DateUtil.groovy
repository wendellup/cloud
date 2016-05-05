package cn.egame.utils

import org.apache.commons.lang.StringUtils
import org.apache.commons.lang.time.DateUtils

import java.text.ParseException
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-11-6
 * Time: 上午9:33
 * To change this template use File | Settings | File Templates.
 */
class DateUtil extends DateUtils {
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static String DATE_FORMAT = "yyyy-MM-dd";
    static String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
    static String DATE_FORMAT_YYYYMM = "yyyyMM";

    def static Date parse(String dateStr, String fmtStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmtStr);
        return simpleDateFormat.parse(dateStr);
    }

    def static Date formateDate(Date date, String fmtStr) {
        String dateS = parseDate(date, fmtStr);
        return parse(dateS, fmtStr);
    }
    /**
     * @Description: 将日期转换成指定格式的字符串
     * @param dateValue 要转换的日期
     * @param fmtStr 转换日期的格式, 默认为:"yyyy-MM-dd"
     * @return String 转换之后的字符串
     * @Author hanjun
     * @Create Date 2013年8月16日
     * @Modified by none
     * @Modified Date
     */
    def static String parseDate(java.util.Date dateValue, String fmtStr) {
        if (dateValue == null) {
            return "";
        }
        if (StringUtils.isEmpty(fmtStr)) {
            fmtStr = DATE_TIME_FORMAT;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
        return fmt.format(dateValue);
    }

    /**
     * 将Long转换成指定格式的字符串
     * @param stamp
     * @param fmtStr
     * @return
     */
    def static String parseDate2String(Long stamp, String fmtStr) {
        if (null == stamp) {
            return "";
        }
        if (StringUtils.isEmpty(fmtStr)) {
            fmtStr = DATE_TIME_FORMAT;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(stamp);
        SimpleDateFormat dateformat = new SimpleDateFormat(fmtStr);
        return dateformat.format(c.getTime());
    }

    /**
     *
     * @param date
     * @param format
     * @return
     */
    public static long parseDateLong(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date).getTime();
    }

    public static long parseDate2Long(Date date) {
        return date.getTime();
    }

    /**
     * @Description: 将日期转换成指定格式的字符串
     * @param dateValue 要转换的日期
     * @param fmtStr 转换日期的格式, 默认为:"yyyy-MM-dd"
     * @return String 转换之后的字符串
     * @Author hanjun
     * @Create Date 2013年8月16日
     * @Modified by none
     * @Modified Date
     */
    def static String parseDateByTimeStamp(java.sql.Timestamp dateValue, String fmtStr) {
        if (dateValue == null) {
            return "";
        }
        if (StringUtils.isEmpty(fmtStr)) {
            fmtStr = DATE_FORMAT;
        }
        SimpleDateFormat fmt = new SimpleDateFormat(fmtStr);
        return fmt.format(dateValue);
    }

    /**
     * 获取当前时间格式
     * @return
     */
    def static getCurrentTime4DB() {
        Date thisDate = new Date();
        return parseDate(thisDate, "");
    }

    /**
     * 获取当前月的第一天
     * @return
     */
   def static Date getFristDayByCurrentMonth() {
       // 默认当前月
       Calendar c = Calendar.getInstance();
       c.add(Calendar.MONTH, 0);
       c.set(Calendar.DAY_OF_MONTH,1);
       return c.getTime();
   }
    /**
     * 获取当前月的最后一天
     * @return
     */
    def static Date getLastDayByCurrentMonth() {
        // 默认当前月
        Calendar c = Calendar.getInstance();
        int value = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, value);
        return c.getTime();
    }

    /**
     * 获取某个月的第一天数据
     * @param dataValue时间值
     * @return返回日期类型
     */
    def static Date getFristDay(String dataValue) {
        Date date = DateUtil.parse(dataValue, DATE_FORMAT_YYYYMMDD);
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取某个月的最后一天数据
     * @param dataValue时间值
     * @return返回日期类型
     */
    def static Date getLastDay(String dataValue) {
        Date date = DateUtil.parse(dataValue, DATE_FORMAT_YYYYMMDD);
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        return calendar.getTime();
    }

    /**
     * 得到某个月的第一天
     * @param year
     * @param month
     * @return
     */
    def static String getFirstDayOfMonth(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * 得到某个月的最后一天
     * @param year
     * @param month
     * @return
     */
    def static String getLastDayOfMonth(int year, int month){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month-1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, value);
        return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
    }

    /**
     * 获取指定天数之前的工作日
     * @param curDate
     * @param num
     * @return
     */
    public static Date getPreWorkDay(Date curDate, int num){
        Calendar now = Calendar.getInstance();
        now.setTime(curDate);
        for (int i = 0; i < num; i++) {
            while(true) {
                now.add(Calendar.DAY_OF_YEAR,-1);
                int week = now.get(Calendar.DAY_OF_WEEK);
                if (week != 7 && week != 1){
                    break;
                }
            }
        }
        return now.getTime();
    }

    public static Integer getDayOfWeek(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static String getYesterday(){
        // 昨天的日期
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        return DateUtil.parseDate(cal.getTime(), DateUtil.DATE_FORMAT);
    }
}
