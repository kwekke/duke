package task;

import exception.DukeException;
import exception.DukeInvalidTaskDateException;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class DeadlineTask extends Task {
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HHmm");
    protected Date time;

    public DeadlineTask(String description, String time) throws DukeException {
        super(description);
        try {
            this.time = format.parse(time);
        } catch (ParseException e) {
            throw new DukeInvalidTaskDateException(time);
        }
    }

    public String toFileString() {
        return "D||" + (this.isDone ? "1||" : "0||")  + this.description + "||" + format.format(this.time);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + time + ")";
    }

}
