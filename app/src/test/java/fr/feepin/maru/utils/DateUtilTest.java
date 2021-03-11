package fr.feepin.maru.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Calendar;

@RunWith(JUnit4.class)
public class DateUtilTest {

    @Test
    public void getDateFromTimeMillis_withSuccess() {
        int hours = 17;
        int minutes = 32;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hours);
        calendar.set(Calendar.MINUTE, minutes);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);

        Assert.assertEquals(calendar.getTimeInMillis(), DateUtil.getDateMillisFromTime(hours, minutes));
    }

}
