package backend.findjob.helper;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;

public class Helper {
    public static Period countDayAgoToNow(Timestamp time_ago)
    {
        LocalDate date_create = time_ago.toLocalDateTime().toLocalDate();
        LocalDate today = LocalDate.now();
        Period period = Period.between(date_create, today);

        return period;
    }
    public static String convertDayAgoToNow(Timestamp time_ago)
    {

        Period period = countDayAgoToNow(time_ago);

        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        if(years == 0 && months == 0) // case is day
        {
            if(days >= 7)
            {
                int week = days%7;
                return week+" weeks";
            }
            return days+" days";
        }
        else if(years == 0) // case month
        {
            return months + " months";
        }
        else{
            return years + " years";
        }
    }

    public static Long convertDayCreateToExpire(Timestamp create_at, Timestamp expire_at)
    {

        LocalDate date_create = create_at.toLocalDateTime().toLocalDate();
        LocalDate date_expire = expire_at.toLocalDateTime().toLocalDate();


        Long days = DAYS.between(date_create,date_expire);
        return days;


    }

    public static Date convertStringToDate(String date_str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(date_str);

        return date;
    }

    public static String removeExtension(String filename) {
        // Find the last dot in the filename
        int lastDotIndex = filename.lastIndexOf(".");

        // If there's a dot and it's not the first character
        if (lastDotIndex != -1 && lastDotIndex > 0) {
            // Extract the substring before the last dot
            return filename.substring(0, lastDotIndex);
        }

        // No extension found, return the original filename
        return filename;
    }


}
