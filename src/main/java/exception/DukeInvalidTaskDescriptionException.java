package exception;

public class DukeInvalidTaskDescriptionException extends DukeException {

    public DukeInvalidTaskDescriptionException(String taskType) {
        super("☹ OOPS!!! The description of a " + taskType + " task cannot be empty.");
    }

}
