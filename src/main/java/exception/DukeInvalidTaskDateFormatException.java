package exception;

public class DukeInvalidTaskDateFormatException extends DukeException {

    public DukeInvalidTaskDateFormatException(String time, String pattern) {
        super(time + " is not a valid date. Use " + pattern);
    }

}
