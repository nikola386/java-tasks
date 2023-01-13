import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/*
Create a program that calculates the number of days between two dates:
•	The dates are entered in the following format '01-11-2012'.
•	The dates are coming as arguments of the program (main method arguments).
•	The output is in the console.
•	Implement the program using two different approaches:
    -	Implement the program using SimpleDateFormat, Date and TimeUnit class.
    -	Implement the program using LocalDate and ChronoUnit class.
 */
public class Main {
    final static String DATE_PATTERN = "dd-MM-yyyy";
    public static void main(String[] args) {
        if(args.length == 0 || args.length > 2) {
            throw new IllegalArgumentException();
        }

        String date1 = args[0];
        String date2 = args[1];

        try {
            long days = usingDateClass(date1, date2);
            System.out.printf("Num of days between %s - %s using Date class: %d%n", date1, date2, days);

            days = usingLocalDateClass(date1, date2);
            System.out.printf("Num of days between %s - %s using LocalDate class: %d%n", date1, date2, days);
        } catch (ParseException pe) {
            System.err.printf("ParseException: %s%n", pe);
        }
    }

    private static long usingDateClass(String date1, String date2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date firstDate = simpleDateFormat.parse(date1);
        Date secondDate = simpleDateFormat.parse(date2);

        long diffInMillis = Math.abs(secondDate.getTime() - firstDate.getTime());
        return TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }

    private static long usingLocalDateClass(String date1, String date2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        LocalDate firstDate = LocalDate.parse(date1, formatter);
        LocalDate secondDate = LocalDate.parse(date2, formatter);

        long days = ChronoUnit.DAYS.between(firstDate, secondDate);
        return Math.abs(days);
    }
}