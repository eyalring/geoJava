package utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public static int getCurrentDayInMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
}
