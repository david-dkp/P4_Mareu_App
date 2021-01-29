package fr.feepin.maru.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    public static String formatToTime(long dateInMillis) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        return simpleDateFormat.format(new Date(dateInMillis));
    }

}
