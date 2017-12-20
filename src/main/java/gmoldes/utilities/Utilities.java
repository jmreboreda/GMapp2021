package gmoldes.utilities;

import javafx.util.StringConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utilities {

    public static StringConverter converter = new StringConverter<LocalDate>() {
        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy");
        @Override
        public String toString(LocalDate date) {
            if (date != null) {
                return dateFormatter.format(date);
            } else {
                return "";
            }
        }
        @Override
        public LocalDate fromString(String string) {
            if (string != null && !string.isEmpty()) {
                return LocalDate.parse(string, dateFormatter);
            } else {
                return null;
            }
        }
    };

    public static Date verifyHourValue(String time){
        Date hour;
        DateFormat hourFormatter = new SimpleDateFormat("HH:mm");
        try{
            hour = hourFormatter.parse(time);
        }catch(ParseException e){
            return null;
        }
        return hour;
    }

    public static String replaceWithUnderscore(String aString){

        return aString.replace(" ", "_")
                .replace(".", "")
                .replace(",","");
    }
}
