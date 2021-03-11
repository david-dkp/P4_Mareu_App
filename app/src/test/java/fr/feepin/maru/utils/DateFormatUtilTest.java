package fr.feepin.maru.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;

@RunWith(JUnit4.class)
public class DateFormatUtilTest {

    @Test
    public void formatToTime_withSuccess() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 32);

        String formattedTime = DateFormatUtil.formatToTime(calendar.getTimeInMillis());

        Assert.assertEquals("16h32", formattedTime);
    }

}
