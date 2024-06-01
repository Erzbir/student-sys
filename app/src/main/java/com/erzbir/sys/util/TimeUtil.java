package com.erzbir.sys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Erzbir
 * @Data: 2024/5/29
 */
public class TimeUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static long getTimestamp(String formatTime) {
        try {
            Date parse = FORMAT.parse(formatTime);
            if (parse == null) {
                return System.currentTimeMillis();
            }
            return parse.getTime();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getFormatTime(long timestamp) {
        return FORMAT.format(new Date(timestamp));
    }
}
