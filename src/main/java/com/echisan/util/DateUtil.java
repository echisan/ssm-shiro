package com.echisan.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String getStringDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return simpleDateFormat.format(new Date());
    }

    public static int getMinutesInterval(String preTime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String nowTime = simpleDateFormat.format(new Date());

        try {
            long pre = simpleDateFormat.parse(preTime).getTime();
            long now = simpleDateFormat.parse(nowTime).getTime();
            return (int) ((now - pre) / (1000 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
            logger.debug("无法转换该格式的时间");
        }

        return -1;
    }


}
