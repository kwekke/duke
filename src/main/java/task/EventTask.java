package task;

import exception.DukeException;
import exception.DukeInvalidTaskDateFormatException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Represents a task which has an event time.
 */
public class EventTask extends Task {
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private Date time;

    /**
     * Creates a EventTime with a given description and time.
     * @param description Task description.
     * @param time Task event date and time in the format of "dd/mm/yyyy hhmm".
     */
    public EventTask(String description, String time) throws DukeException {
        super(description);
        assert !time.equals("");
        try {
            this.time = format.parse(time);
        } catch (ParseException e) {
            throw new DukeInvalidTaskDateFormatException(time);
        }
    }

    public String toFileString() {
        return "E||" + (this.isDone ? "1||" : "0||")  + this.description + "||" + format.format(this.time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +  time  + ")";
    }
}