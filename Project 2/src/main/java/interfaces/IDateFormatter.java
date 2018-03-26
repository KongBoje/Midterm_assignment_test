package interfaces;

import java.util.Date;
import testex.JokeException;

public interface IDateFormatter {
    String getFormattedDate(Date date, String timeZone) throws JokeException;
}
