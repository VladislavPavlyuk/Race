package race.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormatter {

    public static String convertToTime(long time) {
        // Создаем Calendar с временем от начала дня (00:00:00)
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(0);
        cal.add(Calendar.MILLISECOND, (int) time);
        
        // Используем SimpleDateFormat для форматирования интервала времени
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
        return sdf.format(cal.getTime());
    }
}

