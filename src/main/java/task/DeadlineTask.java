package task;

import exception.DukeException;
import exception.DukeInvalidTaskDateFormatException;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

/**
 * Represents a Task which has a deadline.
 */
public class DeadlineTask extends Task {
    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    private Date time;

    /**
     * Creates a DeadlineTask with a given description and time.
     * @param description Task description.
     * @param time Task deadline date and time in the format of "dd/mm/yyyy hhmm".
     */
    public DeadlineTask(String description, String time) throws DukeException {
        super(description);
        assert !time.equals("");
        try {
            this.time = format.parse(time);
        } catch (ParseException e) {
            throw new DukeInvalidTaskDateFormatException(time, format.toPattern());
        }
    }

    public DeadlineTask(String description, Date time) {
        super(description);
        this.time = time;
    }

    public String toFileString() {
        return "D||" + (this.isDone ? "1||" : "0||")  + this.description + "||" + format.format(this.time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + format.format(this.time) + ")";
    }

}
