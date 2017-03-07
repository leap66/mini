package com.qianfan123.minya.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dzq on 2016/10/19.
 */

public class DateUtil {
  public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

  public static String format(Date date, String format) {
    if (IsEmpty.object(date))
      return null;
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
    return sdf.format(date);
  }

  public static Date parse(String date, String format) throws ParseException {
    if (IsEmpty.object(date))
      return null;
    SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
    return sdf.parse(date);
  }

}
