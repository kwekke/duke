package exception;

public class DukeMissingNumberedTaskException extends DukeException {

    public DukeMissingNumberedTaskException(String commandWord) {
        super("Give me a numbered task to " + commandWord);
    }

}
