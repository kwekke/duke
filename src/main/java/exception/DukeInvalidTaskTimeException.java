package exception;

public class DukeInvalidTaskTimeException extends DukeException {

    public DukeInvalidTaskTimeException(String taskType) {
        super("Did you remember to use" + (taskType.equals("deadline") ? " \"/by\"?" : " \"/at\"?"));
    }
}
